package io.issc.android_dev_tutorial_kt

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MainService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //提取数据
        intent?.let {
            val data = it.getStringExtra("data")
            Log.i("MainService", "data: $data")
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        return MainBinder()
    }

    fun task():String {
        Log.i("MainService", "task")
        return "result"
    }

    internal inner class MainBinder : Binder() {
        fun getService(): MainService {
            return this@MainService
        }
    }
}