package com.wayne.ktxs

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.Continuation

suspend inline fun <T> Any.suspendCoroutineWithTimeout(timeout: Long, crossinline block: (Continuation<T>) -> Unit) = withTimeout(timeout) {
    suspendCancellableCoroutine(block = block)
}

suspend inline fun <T> Any.suspendCoroutineReturnWithTimeout(timeout: Long, crossinline block: (Continuation<T>) -> Unit): T? {
    var finalValue: T? = null
    withTimeoutOrNull(timeout) {
        finalValue = suspendCancellableCoroutine(block = block)
    }
    return finalValue
}