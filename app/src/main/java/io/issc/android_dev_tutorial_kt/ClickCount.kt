package io.issc.android_dev_tutorial_kt

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable


/**
 * DataBinding 示例类对象
 */
class ClickCount(cnt:Int):BaseObservable() {

    var cnt:String = cnt.toString()
        @Bindable get() = field
        set(cnt) {
            field = cnt
            notifyChange()
        }

    init {
//        this.cnt = cnt
    }

}