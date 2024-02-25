package io.issc.kotlin_basics

/**
 * 继承Person的Teacher子类
 */
class Teacher(name:String, age:Int, gender:String, subject:String, salary:Number, address: String):Person(name, age, gender, address) {
    var subject = subject
    var salary:Number? = salary //salary is a nullable type

    //offerCourse function returns a DataCourse object
    public fun offerCourse(courseName:String, hasExam:Boolean): DataCourse {
        var course = DataCourse(courseName, hasExam)
        print("teacher $name offers course $course")
        return course
    }

    fun print() {
        println("teacher $name, teaches $subject")
    }
}