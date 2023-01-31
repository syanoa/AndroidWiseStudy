package com.wayne.algorithm.ktxs

import kotlin.reflect.KClass

class Item<T: Any> constructor(val type : KClass<out T>) {
    companion object{
        inline operator fun <reified T: Any> invoke() = Item(T::class)
    }
}