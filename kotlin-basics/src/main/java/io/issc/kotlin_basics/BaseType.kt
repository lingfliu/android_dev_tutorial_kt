package io.issc.kotlin_basics

//可被继承的类用open标注，构造函数为括号中的
open class BaseType(name: String, x: Int) {
    var x = x
    var name = name

    var attr: Double? //可空变量

    //第二构造函数
    constructor(name: String, x: Int, attr: Double) : this(name, x) {
        this.attr = attr
    }


    //其他的初始化
    init {
        attr = 0.0
    }

    public fun validate():Boolean {
        return !name.isEmpty() && x >= 0
    }
}