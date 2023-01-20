package io.issc.kotlin_basics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineTester:ViewModel() {
    fun submit(runnable: Runnable){
        viewModelScope.launch(Dispatchers.IO){ withContext(Dispatchers.IO){ runnable.run() }}
    }
}