package io.issc.kotlin_basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    var coroutineTester = CoroutineTester()
    var idx = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //协程演示
        for (i in 1..100) {
            coroutineTester.submit({ Log.i("kotlin-basics", "coroutine test ${i}".format(i)) })
        }
    }
}