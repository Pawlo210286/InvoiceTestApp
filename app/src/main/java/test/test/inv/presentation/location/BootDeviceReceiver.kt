package test.test.inv.presentation.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class BootDeviceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val serviceIntent = Intent(context, LocationService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }
}