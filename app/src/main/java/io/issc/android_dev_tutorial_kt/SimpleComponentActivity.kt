package io.issc.android_dev_tutorial_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future
import kotlin.random.Random


class SimpleComponentActivity : AppCompatActivity() {
    lateinit var binding: ActivitySimpleComponentBinding
    lateinit var txt: TextView
    lateinit var btn: Button
    var clickCount = ClickCount(1)

    val taskResultList = ArrayList<Future<Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txt = binding.txt
        btn = binding.btn
        binding.clickCount = clickCount

        btn.setOnClickListener {
            clickCount.cnt = (clickCount.cnt.toInt()+1).toString()
        }

    }
}