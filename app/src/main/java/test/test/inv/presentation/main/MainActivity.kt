package test.test.inv.presentation.main

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import test.test.inv.R
import test.test.inv.presentation.base.BaseActivity
import test.test.inv.presentation.location.LocationService
import java.util.*

class MainActivity : BaseActivity() {

    override fun diModule(): Kodein.Module = MainModule.get(this)

    private val router: MainRouter by instance()

    private val sCon = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            (service as? LocationService.LocalBinder)?.service?.startLocationUpdates()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissionAndStartService()

        if (savedInstanceState == null)
            router.showHomeFragment()

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.hasExtra(RESOLUTION_KEY) == true) {
            startIntentSenderForResult(
                intent.getParcelableExtra<PendingIntent>(RESOLUTION_KEY)!!.intentSender,
                RESOLUTION_KEY_REQUEST_CODE, null, 0, 0, 0
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RESOLUTION_KEY_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val intent = Intent(this, LocationService::class.java)
                        bindService(intent, sCon, Context.BIND_AUTO_CREATE)
                    }
                    else -> showPermissionFinishDialog()
                }

            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun checkPermissionAndStartService() {
        if (isCanRunLocationService()) {
            startLocationService()
        } else {
            ActivityCompat.requestPermissions(
                this,
                getLocationsPermissionArray(),
                ACCESS_FINE_LOCATION_REQUEST_CODE
            )
        }
    }

    private fun isCanRunLocationService(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isAccessFineLocationPermissionGranted() && isAccessBackgroundLocationGranted()
        } else {
            isAccessFineLocationPermissionGranted()
        }
    }

    private fun getLocationsPermissionArray(): Array<String> {
        val permissions = mutableListOf<String>()
        if (!isAccessFineLocationPermissionGranted())
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && !isAccessBackgroundLocationGranted())
            permissions.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        return permissions.toTypedArray()
    }

    private fun isAccessFineLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isAccessBackgroundLocationGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            ACCESS_FINE_LOCATION_REQUEST_CODE -> {

                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    startLocationService()
                } else {
                    showPermissionFinishDialog()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun startLocationService() {
        val serviceIntent = Intent(this, LocationService::class.java)
        val pendingIntent = PendingIntent.getService(this, 0, serviceIntent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            Calendar.getInstance().timeInMillis + START_DELAY_IN_MILLIS,
            REPEAT_TIME_IN_MILLIS,
            pendingIntent
        )

        ContextCompat.startForegroundService(this, serviceIntent)
    }

    private fun showPermissionFinishDialog() {
        showDetailsOkDialog(
            title = getString(R.string.warning),
            message = getString(R.string.warning_message)
        ) {
            it.dismiss()
            finish()
        }
    }

    companion object {
        const val ACCESS_FINE_LOCATION_REQUEST_CODE = 5
        const val RESOLUTION_KEY = "key_resolution"
        const val RESOLUTION_KEY_REQUEST_CODE = 44

        const val START_DELAY_IN_MILLIS = 3_000L
        const val REPEAT_TIME_IN_MILLIS = 7_200_000L // 2h
    }

}