package io.issc.kotlin_basics

open class BaseType(code:Int, name:String) {
    var code = code
    var name = name
    var value: Double? = null

    init {
        //其他的初始化
        value = 0.0
    }

    public fun validate():Boolean {
        return !name.isEmpty() && code >= 0
    }

}