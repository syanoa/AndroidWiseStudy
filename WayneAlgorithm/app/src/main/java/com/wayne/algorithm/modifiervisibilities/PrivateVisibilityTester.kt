package com.wayne.algorithm.modifiervisibilities


private class PrivateVisibilityTester {
    private val name = "PrivateVisibilityTester"
    private inner class ClassInside{

    }
}

class PrivateVisibilityTester2{
    inner class ClassInside{
        private var name = "PrivateVisibilityTester2"
    }
    fun perform(){
//        ClassInside().name = "perform"// error cannot access private property in inner class from outside in Kotlin, but Java can.
    }
}
class ClassUsingPrivate{
    fun perform(){
        PrivateVisibilityTester() // it's Ok because in Kotlin a private class is 'file private'
//        PrivateVisibilityTester.ClassInside()// error, cannot visit a private inner class even in a same file
//        val accessTheName = PrivateVisibilityTester().name // error, cannot visit a private property in other class

    }
}

/**
 * not allow to expose extension as public for a private class, because the class is 'private in file'
 */
//fun PrivateVisibilityTester.tryExtension(){
//
//}

/**
 * it's OK
 */
private fun PrivateVisibilityTester.tryExtension(){

}