package io.issc.kotlin_basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import java.util.concurrent.Callable
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    val threadPool = ThreadPool.getInstance()
    var coroutineTester = CoroutineTester()
    var idx = 0

    var kbSensor = KbSensor(1, "sensor1", "model1", "description1")


    //Handler callback示例
    val handler = Handler(Looper.getMainLooper(), {
        Log.d("main", it.arg1.toString() + " " + (it.obj as Bundle).getString("str"))
        false })

    lateinit var txt:View

    var cnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        kbSensor.value = 1.0

        var itest = object:ITest {
            override fun onCall(event: String) {
                Log.d("interface test", event)
            }

            override fun onReturn(event: String): String {
                Log.d("interface test", event)
                return event
            }
        }

        txt = this.findViewById(R.id.txt)

        //Handler 示例 Message传输
        txt.setOnClickListener({
            val msg = Message()
            val bdl = Bundle()
            cnt ++
            bdl.putString("str", cnt.toString())
            msg.arg1 = cnt
            msg.obj = bdl
            handler.sendMessage(msg)
        })

        //协程并发示例
        for (i in 1..100) {
            coroutineTester.submit({ Log.i("suspend test", "${i}".format(i)) })
        }

        //协程flow示例
        coroutineTester.flowTest()

        //协程flow map示例
        coroutineTester.flowMapTest()

        //线程池示例
        for (i in 1..10) {
            threadPool.submit({
                Log.d("thread test", i.toString())
            })
        }

        //callable 线程池示例
        val futureList = ArrayList<Future<Any>>()
        for (i in 1..10) {
            futureList.add(threadPool.submit(Callable{
                Log.d("callable submit" ,i.toString())
                i
            })
            )
        }

        for (future in futureList) {
            Log.d("callable result", future.get().toString())
        }
    }
}