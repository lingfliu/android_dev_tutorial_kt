package io.issc.android_dev_tutorial_kt

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class CoroutineTester:ViewModel() {
    fun submit(runnable: Runnable){
        viewModelScope.launch(Dispatchers.IO){ withContext(Dispatchers.IO){ runnable.run() }}
    }

    suspend fun task(i:Int){
        delay(100L*i)
        Log.i("coroutine at ", i.toString() + " " + System.currentTimeMillis().toString())
    }
    fun flowTest() {
        var valList = ArrayList<Int>()
        for (i in 1..10) {
            valList.add(i)
        }

        viewModelScope.launch(Dispatchers.IO){
            flow {
                for (v: Int in valList) {
                    delay(200)
                    emit(v)
                }
            }
                .collect { v ->
                    Log.i("flow test", v.toString())
                }
        }
//        for (v in valList) {
//            viewModelScope.launch { task(v) }
//        }
    }

    val TAG = "flow map test"
    fun flowMapTest() {
        Log.d(TAG, "begin flow map test")
        viewModelScope.launch(Dispatchers.IO) {
            flow {
                emit(1)
            }
                .onStart { Log.d(TAG, "start") }
                .map { i->2*i }
                .map { i->2*i }
                .map { i-> i.toString() }
                .collect {
                    i->
                    Log.d(TAG, "collect " + i.toString())
                }
        }
    }
}