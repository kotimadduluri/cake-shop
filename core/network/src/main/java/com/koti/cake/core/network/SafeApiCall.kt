package com.koti.cake.core.network

import com.koti.cake.core.domain.AppResult
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.TimeSource

/**
 * Runs an API call, logs how it went and turns any failure into an [AppResult.Failure].
 * Cancellation is rethrown so coroutines can stop normally.
 */
suspend fun <T> safeApiCall(logger: Logger, name: String, call: suspend () -> T): AppResult<T> {
    logger.d(TAG, "$name started")
    val start = TimeSource.Monotonic.markNow()
    return try {
        val data = call()
        logger.d(TAG, "$name succeeded in ${start.elapsedNow().inWholeMilliseconds} ms")
        AppResult.Success(data)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        val error = e.toDataError()
        logger.e(TAG, "$name failed: $error", e)
        AppResult.Failure(error)
    }
}

private const val TAG = "Api"
