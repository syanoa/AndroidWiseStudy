package com.wayne.algorithm.generics

import com.wayne.algorithm.ktxs.wayneLogd


open class SchoolMember(val name:String){
    open val filed1 = "123"
    open fun doThings(){
        wayneLogd("have breakfast")
    }
}

class President(name:String): SchoolMember(name){
    override val filed1: String
        get() = "pink finance"

    override fun doThings() {
        super.doThings()
        wayneLogd("$name manages teachers")
    }
}
class Teacher(name:String): SchoolMember(name){
    override fun doThings() {
        super.doThings()
        wayneLogd("$name teaches students")
    }
}
open class Student(name:String): SchoolMember(name){
    override fun doThings() {
        super.doThings()
        wayneLogd("$name is learning...")
    }
    fun playGames(){

    }
}
class Junior(name:String):Student(name){

}
class GenericsTester<T: SchoolMember> (val entity:T){
    fun memberDoThings(){
        entity.doThings()
    }
}

fun studentStuffs(genericsTester:GenericsTester<out Student>){
    genericsTester.entity.playGames()
}
fun genericsTest(){
    GenericsTester(President("Big poppa")).memberDoThings()
    GenericsTester(Teacher("Teacher")).memberDoThings()
    GenericsTester(Student("Student")).memberDoThings()
    studentStuffs(GenericsTester(Student("Student")))// OK
    studentStuffs(GenericsTester(Junior("Junior student")))// OK as well
//    studentStuffs(GenericsTester(President("Big poppa")))// Not OK

}

class Box<T>(t:T){
    var value = t
}



