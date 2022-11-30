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