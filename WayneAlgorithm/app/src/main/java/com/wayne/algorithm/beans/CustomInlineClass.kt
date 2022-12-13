package com.wayne.algorithm.beans

@JvmInline  //<- which replaces the inline modifier
value class CustomInlineClass(val name:String) {
    // cannot have default accessor because Kotlin would generate "Backing field" to it which is not allowed by inline class
    val shitCount:Int
      get() = name.length


}

inline class CustomInlineClassDeprecated(val name:String){
    // cannot have default accessor because Kotlin would generate "Backing field" to it which is not allowed by inline class
    val shitCount:Int
        get() = name.length
}

private val mPrivateMemberForInlineTest = "private"

@PublishedApi //@PublishedApi annotation is only applicable for internal declaration
internal val mPublishedApiMember = "publishedApi"

val defaultPublicMember = "publicMember"

/**
 * inline function->
 *
 * crossinline modifier-> doesn't allow non-local returns, especially when such lambda is passed to another execution context
 * such as a higher order function that is not inlined, a local object or a nested function
 *
 */
inline fun inlineFunction(crossinline mLambda:()->Unit){
    //ERROR:Public-API inline function cannot access non-public-API 'private val mPrivateMemberForInlineTest
//    mPrivateMemberForInlineTest.length
    mPublishedApiMember.length
    defaultPublicMember.length
    normalFunction {
        mLambda()
    }

    //ERROR: mLambda should be noinline
//    normalFunction(mLambda)
}

fun normalFunction(mLambda:()->Unit){
    mPrivateMemberForInlineTest.length
}