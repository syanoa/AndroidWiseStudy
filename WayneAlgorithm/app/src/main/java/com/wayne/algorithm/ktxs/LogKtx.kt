package com.wayne.algorithm.ktxs

import android.util.Log
import com.wayne.algorithm.BaseApp

@JvmOverloads
fun logv(
    msg: String,
    tag: String,
    showLogFrom: Boolean = true,
    showCurrentThreadName: Boolean = false
) {
    if (BaseApp.isDebug) {
        log(tag, getPackagedMsg(msg, showLogFrom, showCurrentThreadName), "v")
    }
}

@JvmOverloads
fun logd(
    msg: String,
    tag: String,
    showLogFrom: Boolean = true,
    showCurrentThreadName: Boolean = false
) {
    if (BaseApp.isDebug) {
        log(tag, getPackagedMsg(msg, showLogFrom, showCurrentThreadName), "d")
    }
}

@JvmOverloads
fun logi(
    msg: String,
    tag: String,
    showLogFrom: Boolean = true,
    showCurrentThreadName: Boolean = false
) {
    if (BaseApp.isDebug) {
        log(tag, getPackagedMsg(msg, showLogFrom, showCurrentThreadName), "i")
    }
}

@JvmOverloads
fun logw(
    msg: String,
    tag: String,
    showLogFrom: Boolean = true,
    showCurrentThreadName: Boolean = false
) {
    if (BaseApp.isDebug) {
        log(tag, getPackagedMsg(msg, showLogFrom, showCurrentThreadName), "w")
    }
}

@JvmOverloads
fun loge(
    msg: String,
    tag: String,
    showLogFrom: Boolean = true,
    showCurrentThreadName: Boolean = false
) {
    if (BaseApp.isDebug) {
        log(tag, getPackagedMsg(msg, showLogFrom, showCurrentThreadName), "e")
    }
}

fun wayneLogv(msg: String) = logv(msg, tag = "wayne_check")
fun wayneLogd(msg: String) = logd(msg, tag = "wayne_check")
fun wayneLogi(msg: String) = logi(msg, tag = "wayne_check")
fun wayneLogw(msg: String) = logw(msg, tag = "wayne_check")
fun wayneLoge(msg: String) = loge(msg, tag = "wayne_check")


private fun getPackagedMsg(
    msg: String,
    showLogFrom: Boolean,
    showCurrentThreadName: Boolean
) = when {
    showLogFrom -> getStackTraceMsg(msg)
    showCurrentThreadName -> getStackTraceMsg("${Thread.currentThread().name}(id：${Thread.currentThread().id})：$msg")
    else -> msg
}

fun log(tag: String, msg: String, method: String) {
    when (method) {
        "v" -> Log.v(tag, msg)
        "d" -> Log.d(tag, msg)
        "i" -> Log.i(tag, msg)
        "w" -> Log.w(tag, msg)
        "e" -> Log.e(tag, msg)
    }
}

fun getStackTraceMsg(msg: String): String {
    val elements = Thread.currentThread().stackTrace
    val logFileLastIndex = elements.indexOfLast { it.className.contains("LogKtx") }
    val index = when {
        logFileLastIndex == -1 && elements.size > 6 -> 6
        elements.size > logFileLastIndex + 1 -> logFileLastIndex + 1
        else -> -1
    }
    if (index == -1) {
        return msg
    }
    val element = elements[index]
    return "$element：\n$msg"
}