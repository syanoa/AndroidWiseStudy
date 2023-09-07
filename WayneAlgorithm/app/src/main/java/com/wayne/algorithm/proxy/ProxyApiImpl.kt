package com.wayne.algorithm.proxy

import com.wayne.algorithm.ktxs.wayneLogd

class ProxyApiImpl:ProxyApi {
    override fun doSomething1() {
        wayneLogd("Do 1")
    }

    override fun doSomething2() {
        wayneLogd("Do 2")
    }
}