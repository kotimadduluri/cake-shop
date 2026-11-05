package com.koti.cake.core.domain

/** Result of a data operation: either the data or a [DataError]. */
sealed interface AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>
    data class Failure(val error: DataError) : AppResult<Nothing>
}
