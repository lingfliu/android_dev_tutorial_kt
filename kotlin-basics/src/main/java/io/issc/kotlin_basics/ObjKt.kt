package io.issc.kotlin_basics

class ObjKt(name:String, value:Double): BaseType, ListenerK {
    var name:String = name
    var value:Double = value

    var status:Int?

    lateinit var info:String

    init {
        status = 1
    }

    //委托模式
    constructor(name:String):this(name, 0.0)

    fun print() {
        var obj = ObjKt("Hello")
        var obj2 = ObjKt("Hello", 2)

        info = "hello"

        println("name: $name, value: $value info: $info")
    }

    fun loop() {
        var list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        var array = Array(10) { i -> i + 1 }
        var l = MutableList(10) { i -> i + 1 }

        for (i in 1..10) {
            println("i: $i")
        }
    }
    override fun onEvent(event: ObjKt) {
        TODO("Not yet implemented")
    }
}