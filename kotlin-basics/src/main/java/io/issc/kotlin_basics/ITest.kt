package io.issc.kotlin_basics

interface ITest {
    fun onCall(event:String)

    fun onReturn(event:String):String
}