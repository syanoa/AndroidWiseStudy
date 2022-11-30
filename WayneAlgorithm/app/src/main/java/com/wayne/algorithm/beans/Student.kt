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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (name != other.name) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + id
        return result
    }

}
