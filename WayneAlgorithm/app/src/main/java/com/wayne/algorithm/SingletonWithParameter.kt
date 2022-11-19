package com.wayne.algorithm

import android.content.Context

class SingletonWithParameter {

    private constructor(){

    }
    companion object{
        @Volatile private var mInstance:SingletonWithParameter? = null
        fun getInstance(context: Context):SingletonWithParameter = mInstance?: synchronized(this){
            mInstance?: buildBeanProcess().also { mInstance = it }
        }
        private fun buildBeanProcess():SingletonWithParameter = SingletonWithParameter()
    }
}