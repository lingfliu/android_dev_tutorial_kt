package io.issc.kotlin_basics

class Adapter<T>(t:T) {
    var t:T = t
    fun print() {
        println("t: $t")
    }
}