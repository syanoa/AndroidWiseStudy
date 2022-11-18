package com.wayne.algorithm

import com.wayne.algorithm.ktxs.wayneLogd
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        wayneLogd("   application onCreate" )
        instance = this

    }

}