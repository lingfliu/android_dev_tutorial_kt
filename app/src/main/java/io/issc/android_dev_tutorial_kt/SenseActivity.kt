package io.issc.android_dev_tutorial_kt

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Sensor
import android.hardware.Sensor.*
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioManager
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.issc.android_dev_tutorial_kt.databinding.ActivityIoBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivitySenseBinding
import io.issc.android_dev_tutorial_kt.model.MessageBundle
import io.issc.android_dev_tutorial_kt.model.ProjectInfo
import io.issc.android_dev_tutorial_kt.model.ProjectInfoExt
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import java.io.File
import java.net.Socket
import java.util.*


/**
 * 自定义View示例
 */
class SenseActivity : AppCompatActivity() {
    lateinit var binding: ActivitySenseBinding
    lateinit var btn:Button
    lateinit var img:ImageView
    lateinit var sensorList:RecyclerView
    lateinit var sensors: List<Sensor>
    lateinit var sensorMgr:SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //必须在onCreate中调用，否则权限请求会被忽略
        requestPermissions(arrayOf(android.Manifest.permission.HIGH_SAMPLING_RATE_SENSORS), 0)
        binding = ActivitySenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn = binding.btnToCam
        img = binding.photo
        sensorList = binding.sensorList

        sensorMgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensors = sensorMgr.getSensorList(TYPE_ALL)
        val accSensor = sensorMgr.getDefaultSensor(TYPE_ACCELEROMETER)

        sensorMgr.registerListener(object:SensorEventListener{
            override fun onSensorChanged(event: SensorEvent?) {
                Log.i("senseacti", "value " + event?.values?.get(0).toString()  + " at " + event?.timestamp.toString())
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                Log.i("senseacti", "acc changed " + p1.toString())
            }
        }, accSensor, SensorManager.SENSOR_DELAY_UI)
//
//        sensorMgr.registerListener(object:SensorEventListener{
//            override fun onSensorChanged(event: SensorEvent?) {
//                Log.i("senseacti", "value " + event?.values)
//            }
//
//            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
//                Log.i("senseacti", "acc changed " + p1.toString())
//            }
//        }, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL)
        val adapter =  SensorListAdapter(sensors)
        sensorList.adapter = adapter
        sensorList.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        adapter.notifyDataSetChanged()

        btn.text = "拍照"

        btn.setOnClickListener{
            //start audio recording

            val audioMgr = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioMgr.setMode(AudioManager.MODE_IN_COMMUNICATION)
            audioMgr.setSpeakerphoneOn(true)

            val recorder = MediaRecorder()

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            val path = "${externalCacheDir?.absolutePath}/audio.3gp"
            recorder.setOutputFile(path)

            recorder.prepare()
            recorder.start()
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show()

            recorder.stop()
            recorder.release()

            recorder.setOnInfoListener(object:MediaRecorder.OnInfoListener{
                override fun onInfo(p0: MediaRecorder?, p1: Int, p2: Int) {
                    Log.i("senseacti", "info " + p1.toString())
                }
            })
        }

        val caller = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val path = result.data?.getStringExtra("path")
            val decodeFile = BitmapFactory.decodeFile(path)
            val scaledBitmap = Bitmap.createScaledBitmap(decodeFile, 400, 400, true)
            img.setImageBitmap(scaledBitmap)
            val a = 0
        }

        btn.setOnClickListener{
            var intent = Intent(this, CamActivity::class.java)
            caller.launch(intent)
        }

    }

    class TestWorker(appContext: Context, workParams:WorkerParameters)
        :Worker(appContext, workParams) {
        override fun doWork(): Result {
            return Result.success()
        }
    }

    override fun onResume() {
        super.onResume()

        val workRequest = OneTimeWorkRequestBuilder<TestWorker>().build()

        val workMgr = WorkManager.getInstance(applicationContext)
        workMgr.enqueue(workRequest)
    }
}