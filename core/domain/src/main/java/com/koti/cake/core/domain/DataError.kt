package com.koti.cake.core.domain

/** Reasons a data request can fail. The UI decides how to show each one. */
sealed interface DataError {
    data object NoNetwork : DataError
    data object Timeout : DataError
    data class Server(val code: Int) : DataError
    data class Client(val code: Int) : DataError
    data object Parse : DataError
    data object Unknown : DataError
}
