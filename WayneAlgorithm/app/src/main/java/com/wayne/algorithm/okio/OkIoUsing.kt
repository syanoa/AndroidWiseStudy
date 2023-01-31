package com.wayne.algorithm.okio

import android.app.Application
import com.wayne.algorithm.ktxs.wayneLogd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.notify
import okhttp3.internal.wait
import okio.FileSystem
import okio.Path.Companion.toPath

suspend fun Application.getSandBoxPath(dir:String) = getExternalFilesDir(dir)?.apply {
    if(!exists()){
        withContext(Dispatchers.IO){
            mkdir()
        }
    }
}

fun readContent(path:String) = FileSystem.SYSTEM.read(path.toPath()){
    readUtf8()
}

fun updateContent(path:String) = readContent(path).replace("123","321")

fun writeContent(path:String, newContent:String) = FileSystem.SYSTEM.write(path.toPath()){
    writeUtf8(newContent)
}

val threadA = Thread {
    wayneLogd("A start...")
}
val threadB = Thread{
    wayneLogd("B start...")
}

fun threadHandles(){
    threadA.start()
    threadA.wait()
}
