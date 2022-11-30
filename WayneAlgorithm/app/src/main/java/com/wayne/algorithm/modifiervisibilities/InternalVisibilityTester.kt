package com.wayne.algorithm.modifiervisibilities

internal class InternalVisibilityTester {
}

/**
 * expose an internal class with public extension is not allowed
 */
//fun InternalVisibilityTester.doSomething(){
//
//}

/**
 * it's OK
 */
internal fun InternalVisibilityTester.doSomething(){

}