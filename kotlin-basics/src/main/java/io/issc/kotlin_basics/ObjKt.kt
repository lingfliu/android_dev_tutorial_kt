package io.issc.kotlin_basics

//继承类
class ObjKt(name:String, x:Int, id:String): BaseType(name, x), ListenerK {
    var id = id

    //可空变量
    var status:Int?

    //lateinit
    lateinit var listener:ListenerK


    //延迟初始化
    lateinit var info:String

    //第二构造函数
    constructor(name:String, x:Int):this(name, x, "0") {
        status = 0
    }

    init {
        status = 1
    }

    //委托模式构造函数
    constructor(name:String):this(name, 1, "1")

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

    override fun onEvent(msg: String, t: Long) {
        TODO("Not yet implemented")
    }
}