package io.issc.android_dev_tutorial_kt

import android.Manifest
import android.bluetooth.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import io.issc.android_dev_tutorial_kt.databinding.ActivityIoBinding
import io.issc.android_dev_tutorial_kt.model.MessageBundle
import io.issc.android_dev_tutorial_kt.model.ProjectInfo
import io.issc.android_dev_tutorial_kt.model.ProjectInfoExt
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.headers
import kotlinx.coroutines.*
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors


/**
 * 自定义View示例
 */
class CommActivity : AppCompatActivity() {
    lateinit var binding: ActivityIoBinding
    lateinit var btn:Button

    val coroutineTester = CoroutineTester()

    var uuid:UUID = UUID.fromString("00000000-0000-0000-0000-000000000000")
    var bSocket:BluetoothSocket? = null
    lateinit var bAdapter:BluetoothAdapter

    suspend fun testFun() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn = binding.btn

//        Executors.newCachedThreadPool().execute {
//            val httpClient = HttpClient(Android)
//            val paramName = "tn"
//            val paramValue = "78040160_1_dg"
//            val response = httpClient.get<String>("https://www.baidu.com/?$paramName=$paramValue")
//            Log.d("ioacti", response)
//        }
//

//        Thread{
//            //TODO
//            val a = 0
//        }.start()

        runBlocking {
            val httpClient = HttpClient(Android)
            val paramName = "tn"
            val paramValue = "78040160_1_dg"
            val response = httpClient.get<String>("https://www.baidu.com/?$paramName=$paramValue")
            Log.d("ioacti", response)
        }
        CoroutineScope(Dispatchers.IO).launch {
            val httpClient = HttpClient(Android)
            val paramName = "tn"
            val paramValue = "78040160_1_dg"
            val response = httpClient.get<String>("https://www.baidu.com/?$paramName=$paramValue")
            Log.d("ioacti", response)
            runOnUiThread{
                //TODO 在这里执行UI组件刷新
//                img.src = response
            }
        }

        MainScope().launch {
            val httpClient = HttpClient(Android)
            val response = httpClient.get<String>("https://www.baidu.com")
            httpClient.post<String>("https://www.baidu.com"){
                body = "hello"
            }
        }
        GlobalScope.launch {
            //TODO download the main page from www.zhihu.com
            val httpClient = HttpClient(Android)
            val response = httpClient.get<String>("https://www.zhihu.com")
            Log.d("ioacti", response)
        }


//        //runblocking 协程启动
//        runBlocking {
//            withContext(Dispatchers.IO) {
//                val a = 0
//            }
//        }

//        // 启动一个协程
//        CoroutineScope(Dispatchers.IO).launch {
//            val a = 0
//        }



        btn.setOnClickListener{
            // get demo without use keyword
//            runBlocking {
//                val httpClient = HttpClient(Android)
//                val paramName = "tn"
//                val paramValue = "78040160_1_dg"
//                val response = httpClient.get<String>("https://www.baidu.com/?$paramName=$paramValue")
//                Log.d("ioacti", response)
//            }

            //get请求demo
            CoroutineScope(Dispatchers.IO).launch {
                HttpClient(Android).use {
                    val response = it.get<String>("https://www.baidu.com")
                    Log.d("ioacti", response)
                }
            }

            runBlocking {
                HttpClient(Android).use {
                    val response = it.get<String>("https://www.baidu.com")
                    Log.d("ioacti", response)
                }
            }

            //restful get 请求demo
            coroutineTester.submit(object: CoroutineTester.Task {
                override suspend fun run() {
//                    HttpClient(Android) {
//                        install(JsonFeature) {
//                            serializer = GsonSerializer {
//                                serializeNulls()
//                                disableHtmlEscaping()
//                            }
//                        }
//                    }.use {
//                        val response = it.get<MessageBundle<List<ProjectInfo>>>("http://plaf.kingroad.com:8059/api/pdb/project/info/all"){
//                            header("Authorization", "auth123")
//                        }
//                        Log.d("ioacti", response.message.toString())
//                    }


                    HttpClient(Android) {
                        install(JsonFeature) {
                            serializer = GsonSerializer {
                                serializeNulls()
                                disableHtmlEscaping()
                            }
                        }
                    }
                        .use {
                            val response =
                                it.get<MessageBundle<List<ProjectInfo>>>("http://plaf.kingroad.com:8059/api/pdb/project/info/all")
                            Log.d("ioacti", response.message.toString())
                        }
                }
            })

            coroutineTester.submit(object: CoroutineTester.Task {
                override suspend fun run() {
                    HttpClient(Android).use {
                        val response = it.get<String>("https://www.baidu.com")
                        Log.d("ioacti", response)
                    }
                }
            })

            coroutineTester.submit(object: CoroutineTester.Task {

                override suspend fun run() {
                            HttpClient(Android).use {
                                val response = it.get<String>("https://www.baidu.com")
                                Log.d("ioacti", response)
                            }
                }
            })

//            CoroutineScope(Dispatchers.IO).launch {
//            }


//            //restful post 请求demo
//            runBlocking {
//                HttpClient(Android){
//                    defaultRequest {
//                        header("Content-Type", "application/json")
//                        header("Authorization","auth123")
//                    }
//
//                    install(JsonFeature){
//                        serializer = GsonSerializer{
//                            serializeNulls()
//                            disableHtmlEscaping()
//                        }
//                    }
//                }
//                    .use {
//                        val response = it.post<MessageBundle<ProjectInfoExt>>("http://plaf.kingroad.com:8059/api/pdb/project/ext/info/update"){
//                            body = ProjectInfoExt("1480853307463585800", 12.00000, 12.0000 )
//                        }
//                        Log.d("ioacti", response.toString())
//                    }
//            }

        }


        //蓝牙 BLE demo
        val bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        val bAdapter = bluetoothManager.adapter
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_SCAN
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            bAdapter.startDiscovery()
        }
        else {
            requestPermissions(arrayOf(Manifest.permission.BLUETOOTH_SCAN), 1)
        }

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val device = intent?.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                if (ActivityCompat.checkSelfPermission(
                        this@CommActivity,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                else {
                    //bluetooth socket demo
                    bSocket = device?.createRfcommSocketToServiceRecord(uuid)
                    bSocket?.connect()
                    val bs = bSocket?.inputStream?.readBytes()
                    val output = PrintWriter(bSocket?.outputStream)
                    output.println("hello from android")
                    output.flush()
                    bSocket?.close()

//                    device?.name?.let { Log.i("ioacti", it) }
                    device?.connectGatt(this@CommActivity, false, object : BluetoothGattCallback() {
                        override fun onConnectionStateChange(
                            gatt: BluetoothGatt?,
                            status: Int,
                            newState: Int
                        ) {
                            super.onConnectionStateChange(gatt, status, newState)
                            if (newState == BluetoothProfile.STATE_CONNECTED) {
                                if (ActivityCompat.checkSelfPermission(
                                        this@CommActivity,
                                        Manifest.permission.BLUETOOTH_CONNECT
                                    ) != PackageManager.PERMISSION_GRANTED
                                ) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return
                                }
                                gatt?.discoverServices()
                            }
                        }

                        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
                            super.onServicesDiscovered(gatt, status)
                            if (status == BluetoothGatt.GATT_SUCCESS) {
                                val service = gatt?.getService(uuid)
                                val characteristic = service?.getCharacteristic(uuid)
                                if (ActivityCompat.checkSelfPermission(
                                        this@CommActivity,
                                        Manifest.permission.BLUETOOTH_CONNECT
                                    ) != PackageManager.PERMISSION_GRANTED
                                ) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return
                                }
                                gatt?.setCharacteristicNotification(characteristic, true)
                                val descriptor = characteristic?.getDescriptor(uuid)
                                descriptor?.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                                gatt?.writeDescriptor(descriptor)
                            }
                        }

                        override fun onCharacteristicChanged(
                            gatt: BluetoothGatt?,
                            characteristic: BluetoothGattCharacteristic?
                        ) {
                            super.onCharacteristicChanged(gatt, characteristic)
                            val value = characteristic?.value
                            Log.i("ioacti", value.toString())
                        }
                    })
                }
            }
        }, filter)

        btn.setOnClickListener{
            //socket client demo
            runBlocking {
                withContext(Dispatchers.IO) {
                    val socket = Socket("10.0.2.2", 9009) //
//                    val socket = Socket("localhost", 9009) //
//                    val socket = Socket("127.0.0.1", 9009) //
                    socket.soTimeout = 10000
                    socket.keepAlive = true
                    socket.tcpNoDelay = true

                    socket.connect(socket.remoteSocketAddress, 10000)
                    val bytes = socket.getInputStream().readBytes()
                    val receiveMessage = bytes.toString()
                    Log.i("commacti", receiveMessage)

                    socket.getOutputStream().write("hello from android".toByteArray())

                    socket.close()
                }
            }
        }

    }
}