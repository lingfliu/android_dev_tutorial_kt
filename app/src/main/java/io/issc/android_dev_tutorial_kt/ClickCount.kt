package io.issc.android_dev_tutorial_kt

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ClickCount(cnt:Int):BaseObservable() {

    @get:Bindable
    var cnt:String = cnt.toString()
    set(cnt) {
        field = cnt
//        notifyChange()
    }

    init {
//        this.cnt = cnt
    }

}