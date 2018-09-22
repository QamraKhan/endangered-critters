package my.com.toru.critter

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager
import android.util.Log

class TestService : Service() {

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.w("TestService", "onStartCommpand")
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("com.test.Broadcast"))
        return START_STICKY
    }
}