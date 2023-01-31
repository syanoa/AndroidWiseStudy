package com.wayne.algorithm.changeskinbyresourcesapk

import android.content.res.AssetManager

object CustomAssetLoader {

    fun doLoad(){
        val assetManager:AssetManager = AssetManager::class.java.newInstance()
    }
}