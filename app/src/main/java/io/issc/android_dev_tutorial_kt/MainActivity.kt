package io.issc.android_dev_tutorial_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import java.util.concurrent.Future
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var txt: TextView
    lateinit var btn: Button
    var clickCount = ClickCount(1)

    val threadPool = ThreadPool.getInstance()
    val taskResultList = ArrayList<Future<Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txt = binding.txt
        btn = binding.btn
        binding.clickCount = clickCount

        btn.setOnClickListener {
            clickCount.cnt = (clickCount.cnt.toInt()+1).toString()
        }

        for (i in 1..100) {
            val result = threadPool.submit({
                Thread.sleep(100)
                Log.i("kotlin-test", i.toString())
            })

            taskResultList.add(result)
        }

        threadPool.submit({
            while (taskResultList.size > 0) {
                synchronized(taskResultList) {
                    for (res in taskResultList) {
                        if (res.isDone) {
                            Log.i("kotlin-test", "finished " + res.toString())
                            taskResultList.remove(res)
                        }
                    }
                }
            }
        })

    }
}