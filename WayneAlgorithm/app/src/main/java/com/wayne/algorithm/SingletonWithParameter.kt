package com.wayne.algorithm

import android.content.Context
import com.wayne.algorithm.ktxs.wayneLogd

class SingletonWithParameter {

    private constructor(){
        wayneLogd(" SingletonWithParameter get initialized")
    }
    companion object{
        @Volatile private var mInstance:SingletonWithParameter? = null
        fun getInstance(context: Context):SingletonWithParameter = mInstance?: synchronized(this){
            mInstance?: buildBeanProcess().also { mInstance = it }
        }
        private fun buildBeanProcess():SingletonWithParameter = SingletonWithParameter()
    }
}