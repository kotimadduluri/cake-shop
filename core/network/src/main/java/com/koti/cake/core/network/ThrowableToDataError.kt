package com.koti.cake.core.network

import com.koti.cake.core.domain.DataError
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

internal fun Throwable.toDataError(): DataError = when (this) {
    is SocketTimeoutException -> DataError.Timeout
    is IOException -> DataError.NoNetwork
    is HttpException -> if (code() >= 500) DataError.Server(code()) else DataError.Client(code())
    is SerializationException -> DataError.Parse
    else -> DataError.Unknown
}
