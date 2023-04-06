package io.issc.android_dev_tutorial_kt

import android.content.Context
import android.content.ContextParams
import android.graphics.Bitmap
import android.hardware.Sensor.TYPE_ALL
import android.hardware.SensorManager
import android.hardware.camera2.CameraMetadata.LENS_FACING_BACK
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Surface
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import io.issc.android_dev_tutorial_kt.databinding.ActivityCameraBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityComplexComponentBinding
import java.io.File
import java.security.Permission
import java.util.concurrent.Executors
import java.util.jar.Manifest


class CamActivity : AppCompatActivity() {
    lateinit var binding: ActivityCameraBinding

    lateinit var viewFinder: PreviewView
    lateinit var btnShoot: Button
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>


    private lateinit var sensorMgr:SensorManager


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //这里检查permission请求结果
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewFinder = binding.preview
        btnShoot = binding.btnShoot

        sensorMgr  = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        var sensorList = sensorMgr.getSensorList(TYPE_ALL)

        requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 0)

        cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(this))


    }

    fun bindPreview(cameraProvider: ProcessCameraProvider) {
        val preview = Preview.Builder()
            .build()
        val imageCapture = ImageCapture.Builder()
            .build()
        val imageAnalysis = ImageAnalysis.Builder()
            .build()
        var cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT).build()
        preview.setSurfaceProvider(viewFinder.surfaceProvider)
//        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)
        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, imageCapture,
            imageAnalysis, preview)
        btnShoot.setOnClickListener{
            val extDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath
            val path = extDir + File.separator + System.currentTimeMillis() + ".png"
            val photoFile = File(path)
            val outFileOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
            val cameraExecutor = Executors.newSingleThreadExecutor()
            imageCapture.takePicture(outFileOptions, cameraExecutor, object:ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri.toString()?: path
                    Log.i("camacti", "shot picture saved in " + path)
                    intent.putExtra("path", path)
                    setResult(1, intent)
                    finish()
                }

                override fun onError(exception: ImageCaptureException) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}