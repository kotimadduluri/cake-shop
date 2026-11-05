package com.koti.cake.feature.cakes.data

import com.koti.cake.core.network.Logger

internal class FakeLogger : Logger {
    override fun d(tag: String, message: String) = Unit
    override fun e(tag: String, message: String, throwable: Throwable?) = Unit
}
