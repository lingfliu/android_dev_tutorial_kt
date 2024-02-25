package io.issc.kotlin_basics

/**
 * 泛型类
 */
class GenericClass<T>(t:T) {
    var t:T = t
    fun print() {
        println("t: $t")
    }
}