package io.issc.kotlin_basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    var coroutineTester = CoroutineTester()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        coroutineTester.submit({Log.i("kotlin-basics", "coroutine test")})
    }
}