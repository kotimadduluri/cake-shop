package com.koti.cake.core.network

/** Simple logging contract so code can log without depending on android.util.Log. */
interface Logger {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String, throwable: Throwable? = null)
}
