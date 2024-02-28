package io.issc.kotlin_basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import kotlinx.coroutines.delay
import kotlinx.coroutines.*
import java.lang.System.currentTimeMillis

class MainActivity : AppCompatActivity() {
    /*kotlin 基础代码示例*/
    // controlling examples

    //1 自动推导变量
    var x = 1
    val y = 2

    var list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) //定长列表
    var varList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) //可变列表

    //2 可空变量
    var str:String?= "hello"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //3 循环 控制
        for (i in 1..10) {
            Log.d("loop test", i.toString())
        }

        //kotlin 没有switch语句，但可以使用标签和continue 跳转
        switch@ for (i in 1..10) {
            if (i == 5) {
                continue@switch
            }
            Log.d("switch test", i.toString())
        }

        //4 let, also, with: 用于缩减空指针判断
        str?.let {
            Log.d("nullable test", it)
        }

        str?.also { Log.d("nullable test", it) }

        with(str) {
            this?.let { Log.d("nullable test", it) }
        }

        //5 is 类型判断
        if (str is String) {
            str?.let { Log.d("is test", it) }
        }

        //6 异常
        try {
            throw Exception("test")
        } catch (e: Exception) {
            e.message?.let { Log.d("exception test", it) }
        }

        //7 类与对象
        val obj = ObjKt("name", 1, "id")
        obj.id.let { Log.d("object test", it) }

        //8 lambda 算子
        var lambda = { x: Int, y: Int -> x + y }
        lambda(1, 2).let { Log.d("lambda test", it.toString()) }

        //9 协程
        GlobalScope.launch {
            var x : Int = 1 //有初始化时Int可省略

            val xUnmutableList = List(10) { i -> i + 1 }
            val xMutableList = mutableListOf<Int>(10)
            xMutableList.add(1)
            var xArray = Array(10) { i -> i + 1 }

            for (i in 1..10) {
                delay(  1000L)
                x += i
                Log.d("variable test", "x: $x at: " + (currentTimeMillis()))
            }

            x = 0
            xUnmutableList.forEach lit@{
                if (it == 5) return@lit
                x += it
                Log.d("variable test", "x: $x at: " + (currentTimeMillis()))
            }
        }

//        var itest = object:ITest {
//            override fun onCall(event: String) {
//                Log.d("interface test", event)
//            }
//
//            override fun onReturn(event: String): String {
//                Log.d("interface test", event)
//                return event
//            }
//        }
//
        //lambda 表达式
        var txt = findViewById<View>(R.id.txt)
        txt.setOnClickListener(View.OnClickListener {
            Log.d("interface test", "click")
        })
//
//        //Handler 示例 Message传输
//        txt.setOnClickListener({
//            val msg = Message()
//            val bdl = Bundle()
//            cnt ++
//            bdl.putString("str", cnt.toString())
//            msg.arg1 = cnt
//            msg.obj = bdl
//            handler.sendMessage(msg)
//        })
//
//        //协程并发示例
//        for (i in 1..100) {
//            coroutineTester.submit({ Log.i("suspend test", "${i}".format(i)) })
//        }
//
//        //协程flow示例
//        coroutineTester.flowTest()
//
//        //协程flow map示例
//        coroutineTester.flowMapTest()
//
//        //线程池示例
//        for (i in 1..10) {
//            threadPool.submit({
//                Log.d("thread test", i.toString())
//            })
//        }
//
//        //callable 线程池示例
//        val futureList = ArrayList<Future<Any>>()
//        for (i in 1..10) {
//            futureList.add(threadPool.submit(Callable{
//                Log.d("callable submit" ,i.toString())
//                i
//            })
//            )
//        }
//
//        for (future in futureList) {
//            Log.d("callable result", future.get().toString())
//        }
    }
}