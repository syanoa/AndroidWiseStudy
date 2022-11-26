package com.wayne.algorithm

import com.wayne.algorithm.ktxs.wayneLogd

class SingletonByLazy private constructor() {

    init {
        wayneLogd("SingletonByLazy get initialized")
    }
    companion object{
        val instance by lazy {
            //some initializations
            SingletonByLazy()
        }
    }
}