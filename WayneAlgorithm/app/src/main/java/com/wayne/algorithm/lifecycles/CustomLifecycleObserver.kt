package com.wayne.algorithm.lifecycles

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.*

class CustomLifecycleObserver:DefaultLifecycleObserver {
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onResume(owner: LifecycleOwner) {
        stopScope()
        scope.launch {
            doSomethingSuspend()
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        stopScope()
    }

    suspend fun doSomethingSuspend(){

    }
    private fun stopScope(){
        if(scope.isActive){
            scope.cancel()
        }
    }
}