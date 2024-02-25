package io.issc.kotlin_basics

class KbSensor(code: Int, name:String) : BaseType(code, name) {

    var model: String? = null
    var description:String? = null

    constructor(code:Int, name:String, model:String) : this(code, name) {
        this.model = model

        val sensor = KbSensor(1, "sensor1", "model1", "description1")

        sensor.value = 1.0
    }

    constructor(code:Int, name:String, model:String, description:String) : this(code, name, model) {
        this.description = description
    }
}