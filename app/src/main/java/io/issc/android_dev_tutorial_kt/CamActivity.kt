package io.issc.android_dev_tutorial_kt

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import io.issc.android_dev_tutorial_kt.databinding.ActivityComplexComponentBinding


class CamActivity : AppCompatActivity() {
    lateinit var binding: ActivityComplexComponentBinding

    lateinit var spinner:Spinner
    lateinit var webView: WebView
    var selectList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplexComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}