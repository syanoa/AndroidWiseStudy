package com.wayne.algorithm.beans

import com.wayne.algorithm.ktxs.wayneLogd

class Student(var name:String) {
    var id: Int = -1

    companion object{
        init {
            wayneLogd("see what's going on here")
        }
    }
    init{
        wayneLogd(" what the fuck $name")
    }

    constructor(secondName: String, id:Int) : this(secondName){
        this.id = id
    }
}