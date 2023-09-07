package com.wayne.algorithm.proxy

import com.wayne.algorithm.ktxs.wayneLogd
import java.lang.reflect.Proxy

object ProxyManager {
    inline fun <reified T> createProxyImpl(service: T): T {
        val clazz = T::class.java
        return Proxy.newProxyInstance(clazz.classLoader, arrayOf(clazz)) { _, method, args ->
            wayneLogd("proxy impl start...")
            val res = if (args == null) method.invoke(service) else method.invoke(service, *args)
            wayneLogd("proxy impl end...")
        } as T
    }
}