package io.issc.kotlin_basics

class ObjKt(name:String, value:Double): ListenerK {
    var name:String = name
    var value:Double = value

    //可空变量
    var status:Int?

    var listener:ListenerK? = null

    fun setListener(listener: () -> Unit) {
        this.listener = listener
    }

    //延迟初始化
    lateinit var info:String

    constructor(name:String, status:Int):this(name, 0.0) {
        this.status = status
    }

    init {
        status = 1

    }



    //委托模式
    constructor(name:String):this(name, 0.0)

    fun demo() {
        /*
         *  这里展示了java与kotlin之间的互操作
         * */
        var objJava = ObjJava("java")
        var res = objJava.test()
        var objKt = ObjKt("Hello")
        var objjava2 = ObjJava("Hello", 2.0)
        info = "hello"
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