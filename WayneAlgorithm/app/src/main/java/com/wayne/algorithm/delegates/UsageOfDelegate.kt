package com.wayne.algorithm.delegates

import kotlin.reflect.KProperty

fun usingDelegate(){
    val s: String by CustomDelegate()
}

sealed interface SuperDao
 class FamilyDao: SuperDao
 class UserDao:SuperDao
 class DeviceDao:SuperDao
class ImagineDelegate<T>{
}
inline operator fun<reified T:SuperDao>  ImagineDelegate<T>.getValue(thisRef: Any?, property: KProperty<*>): T {
    return when(T::class.java){
        FamilyDao::class.java-> FamilyDao() as T
        UserDao::class.java-> UserDao() as T
        DeviceDao::class.java->DeviceDao() as T
        else ->DeviceDao() as T
    }
}
fun <T>delegateFun():ImagineDelegate<T>{
    return ImagineDelegate()
}
val famDao by delegateFun<FamilyDao>()
