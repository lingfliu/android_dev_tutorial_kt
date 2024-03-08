package io.issc.kotlin_basics

class KbSensor(name:String, code: Int) : BaseType(name, code) {

    var model: String? = null
    var description:String? = null

    constructor(code:Int, name:String, model:String) : this(name, code) {
        this.model = model

        val sensor = KbSensor(1, "sensor1", "model1", "description1")

    }

    constructor(code:Int, name:String, model:String, description:String) : this(code, name, model) {
        this.description = description
    }
}