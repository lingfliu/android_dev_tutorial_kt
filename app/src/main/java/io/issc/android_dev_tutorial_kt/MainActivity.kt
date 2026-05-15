package io.issc.android_dev_tutorial_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.TextView
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import java.util.concurrent.Future
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var txt: TextView
    lateinit var btn: Button
    var clickCount = ClickCount(1)

    val taskResultList = ArrayList<Future<Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txt = binding.txt
        btn = binding.btn
        val btn_reset = binding.btnReset

        btn_reset.setOnClickListener{

        }

        binding.toggleButton.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(btn:CompoundButton, changed: Boolean) {
            }
        })


        binding.clickCount = clickCount

        btn_reset.setOnClickListener{
            val cnt = 0
            clickCount.cnt = cnt.toString()
        }

//        btn.setOnClickListener {
//            clickCount.cnt = (clickCount.cnt.toInt()+1).toString()
//        }

    }
}