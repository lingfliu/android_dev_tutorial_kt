package io.issc.android_dev_tutorial_kt

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class TcpCli {
    lateinit var inputStream : InputStream
    lateinit var outputStream : OutputStream

    fun connect(ip:String, port:Int) {
        val socket = Socket(ip, port)
        socket.connect(socket.remoteSocketAddress, 10000)
        inputStream = socket.getInputStream()
        outputStream = socket.getOutputStream()

        GlobalScope.launch {
            task_rx()
        }

        GlobalScope.launch {
            task_tx()
        }
    }
    fun task_rx() {
        while (true) {
            val buffer = ByteArray(1024)
            val size = inputStream.read(buffer)
            if (size > 0) {
                val data = buffer.copyOfRange(0, size)
                println("Received: ${String(data)}")
            }
        }
    }

    fun task_tx() {
        while (true) {
            val data = readLine()!!.toByteArray()
            outputStream.write(data)
        }

    }
}