package com.wayne.algorithm.modifiervisibilities

import com.wayne.algorithm.ktxs.wayneLogd

open class PropertyVisibilityTester {
    /**
     * in kotlin , identifier 'protected' is visible to subclass and also only in class which is different with Java.
     * if parent class is not open(which is final), the identifier 'protected' becomes useless and private because there is no subclass can invoke it.
     */
    protected var parentField1:String = "parent"
    protected class AAA{
        fun primitiveFunction(){
            wayneLogd("I am in the primitive function")
        }
    }
    protected open class BBB{
        protected var fieldInside:String = "BBB"
    }
}
class SubclassTester : PropertyVisibilityTester(){
    fun invokeAAA(){
        parentField1 = "subclass"
        AAA().also {
            it.primitiveFunction()
        }
    }

    /**
     * THIS is not allowed in Kotlin because default modifier in Kotlin is 'public' but the parent class is protected
     */
//    class BBBSubclass: BBB(){
//
//    }
    /**
     * therefore to inherit from a parent class which is protected, we need to make subclass protected (or private)as well.
     */
    private class BBBSubclass: BBB(){ // both modifiers of protected or private are OK
        fun perform(){
            fieldInside = "BBBSubclass"
        }
    }

}
fun test(){
//    PropertyVisibilityTester().parentField1 //This is not allowed because in Kotlin protected is class-access and subclass-access
}