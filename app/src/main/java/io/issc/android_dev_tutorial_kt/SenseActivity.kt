package io.issc.android_dev_tutorial_kt

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_ALL
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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
        binding = ActivitySenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn = binding.btnToCam
        img = binding.photo
        sensorList = binding.sensorList

        sensorMgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensors = sensorMgr.getSensorList(TYPE_ALL)
        sensorMgr.registerListener(object:SensorEventListener{
            override fun onSensorChanged(event: SensorEvent?) {
                Log.i("senseacti", "value " + event?.values)
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                Log.i("senseacti", "acc changed " + p1.toString())
            }
        }, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL)
        val adapter =  SensorListAdapter(sensors)
        sensorList.adapter = adapter
        sensorList.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        adapter.notifyDataSetChanged()

        btn.text = "拍照"

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

    override fun onResume() {
        super.onResume()

//        sensorMgr.registerListener(object:SensorEventListener{
//            override fun onSensorChanged(event: SensorEvent?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
//                TODO("Not yet implemented")
//            }
//        }, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL)

    }
}