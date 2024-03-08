package io.issc.layout

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ClickCount(cnt:Int): BaseObservable() {
    var cnt:String = cnt.toString()
        var _cnt = cnt

        @Bindable get() = field
        set(value) {
            field = value
            notifyChange()
        }

    fun add() {
        _cnt ++
        cnt = _cnt.toString()
        notifyChange()
    }
}