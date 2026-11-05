package com.koti.cake.core.network

import android.util.Log

/** Writes to Logcat in debug builds only. */
class AndroidLogger : Logger {

    override fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.d(tag, message)
    }

    override fun e(tag: String, message: String, throwable: Throwable?) {
        if (BuildConfig.DEBUG) Log.e(tag, message, throwable)
    }
}
