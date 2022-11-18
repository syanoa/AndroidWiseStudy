package com.wayne.algorithm

import android.app.Application

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseApp : Application() {
    companion object {
        val isDebug = BuildConfig.DEBUG
        lateinit var instance: BaseApp
        private val coroutineScope = CoroutineScope(Dispatchers.Main)

        fun launch(
            context: CoroutineContext = EmptyCoroutineContext,
            start: CoroutineStart = CoroutineStart.DEFAULT,
            block: suspend CoroutineScope.() -> Unit
        ) {
            coroutineScope.launch(context, start, block)
        }


        // app 沙盒路径 + /dir
        fun getSandboxPath(dir: String = "") = instance.getExternalFilesDir(dir)?.apply {
            if (!exists()) {
                mkdir()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()


    }
}

