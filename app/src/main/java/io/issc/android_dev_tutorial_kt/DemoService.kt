package io.issc.android_dev_tutorial_kt

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class DemoService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        intent?.let {
            val data = it.getStringExtra("data")
            Log.i("DemoService", "data: $data")

            //自动执行任务
            task()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return DemoBinder()
    }

    fun task(){
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    inner class DemoBinder : Binder() {
        fun getService(): DemoService {
            return this@DemoService
        }
    }
}