package test.test.inv.presentation.location

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.*
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import test.test.inv.R
import test.test.inv.domain.data.location.Location
import test.test.inv.domain.usecase.location.LocationUseCase
import test.test.inv.presentation.main.MainActivity
import test.test.inv.presentation.util.toRequestFormat
import java.util.*


open class LocationService : Service(), KodeinAware {
    //region Kodein
    private val _parentKodein: Kodein by closestKodein()

    override val kodein: Kodein = Kodein.lazy {
        extend(_parentKodein, true)
    }

    //endregion

    private val locationUseCase: LocationUseCase by instance()

    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }
    private val notificationManager: NotificationManager by lazy {
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
    private val locationListener = LocationListener()
    private var canRequestLocation = true
    private var wakeLock: PowerManager.WakeLock? = null

    private lateinit var notificationBuilder: NotificationCompat.Builder

    private var binder = LocalBinder()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANEL_ID)

        start()
        setUpWakeLock()
        startLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        wakeLock?.let {
            if (it.isHeld) it.release()
        }
    }

    protected open fun buildLocationRequest(): LocationRequest? {
        return LocationRequest.create()?.apply {
            interval = LOCATION_INTERVAL
            fastestInterval = FASTEST_INTERVAL
            maxWaitTime = LOCATION_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    open fun startLocationUpdates() {
        canRequestLocation = true
        val request = buildLocationRequest()
        if (request != null) {
            val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(request)
            LocationServices.getSettingsClient(this)
                .checkLocationSettings(builder.build())
                .addOnCompleteListener {
                    if (canRequestLocation) {
                        fusedLocationProviderClient.requestLocationUpdates(
                            request,
                            locationListener,
                            Looper.getMainLooper()
                        )
                    }
                }.addOnFailureListener { exception ->
                    exception.printStackTrace()
                    if (exception is ResolvableApiException) {
                        stopLocationUpdates()
                        try {
                            when (exception.statusCode) {
                                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                                    val intent = Intent(this, MainActivity::class.java)
                                        .putExtra(MainActivity.RESOLUTION_KEY, exception.resolution)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                }
                                else -> stopSelf()
                            }
                        } catch (sendEx: IntentSender.SendIntentException) {
                            // Ignore the error.
                        }
                    }
                }
        }
    }

    protected open fun stopLocationUpdates() {
        canRequestLocation = false
        fusedLocationProviderClient.removeLocationUpdates(locationListener)
    }

    protected open fun start() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationApi26AndStart()
        } else {
            createNotificationAndStart()
        }
    }

    @SuppressLint("WakelockTimeout")
    private fun setUpWakeLock() {
        wakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(
                    PowerManager.PARTIAL_WAKE_LOCK,
                    "BackgroundLocationService::lock"
                ).apply {
                    acquire()
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationApi26AndStart() {
        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANEL_ID,
            NOTIFICATION_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.setSound(null, null)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationChannel.setShowBadge(false)
        notificationManager.createNotificationChannel(notificationChannel)
        val notification = notificationBuilder
            .setContentTitle(getString(R.string.app_name))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setSound(null)
            .setChannelId(NOTIFICATION_CHANEL_ID)
            .build()
        startForeground(SERVICE_ID, notification)
    }


    private fun createNotificationAndStart() {
        val notification = notificationBuilder
            .setContentTitle(getString(R.string.app_name))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setSound(null)
            .build()
        startForeground(SERVICE_ID, notification)
    }


    private inner class LocationListener : LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            GlobalScope.launch {
                locationUseCase.updateLocations(
                    Location(
                        id = System.currentTimeMillis(),
                        lat = locationResult.lastLocation.latitude.toString(),
                        lon = locationResult.lastLocation.longitude.toString(),
                        data = Date().toRequestFormat()
                    )
                )
            }
        }
    }


    inner class LocalBinder : Binder() {
        internal
        val service: LocationService
            get() = this@LocationService
    }


    companion object {
        private const val LOCATION_INTERVAL = 30_000L
        private const val FASTEST_INTERVAL = LOCATION_INTERVAL
        private const val NOTIFICATION_CHANEL_ID = "63FeQEXEsb"
        private const val NOTIFICATION_NAME = "LOCATION_INFO"
        private const val SERVICE_ID = 87_532
    }
}