package io.issc.kotlin_basics

class KbSensor(code: Int, name:String) : BaseType(code, name) {

    var model: String? = null

    constructor(code:Int, name:String, model:String) : this(code, name) {
        this.model = model
    }
}