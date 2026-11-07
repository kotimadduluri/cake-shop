package com.koti.cake.core.network

class FakeLogger : Logger {
    val debugMessages = mutableListOf<String>()
    val errorMessages = mutableListOf<String>()

    override fun d(tag: String, message: String) {
        debugMessages += message
    }

    override fun e(tag: String, message: String, throwable: Throwable?) {
        errorMessages += message
    }
}
