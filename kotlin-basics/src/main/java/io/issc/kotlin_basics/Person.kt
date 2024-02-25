package io.issc.kotlin_basics

/**
 * Person父类
 */
open class Person(name:String, age: Int, gender: String, address: String){
    var name = name
    var age = age
    var gender = gender
    var address = address

    override fun toString(): String {
        return "name: $name, age: $age, gender: $gender, addr: $address"
    }
}
