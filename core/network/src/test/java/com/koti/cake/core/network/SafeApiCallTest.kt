package com.koti.cake.core.network

import com.koti.cake.core.domain.AppResult
import com.koti.cake.core.domain.DataError
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class SafeApiCallTest {

    private val logger = FakeLogger()

    @Test
    fun `success returns the data and logs it`() = runTest {
        val result = safeApiCall(logger, "getCakes") { "cakes" }

        assertEquals(AppResult.Success("cakes"), result)
        assertTrue(logger.debugMessages.last().startsWith("getCakes succeeded"))
    }

    @Test
    fun `failure returns the mapped error and logs it`() = runTest {
        val result = safeApiCall<String>(logger, "getCakes") { throw IOException() }

        assertEquals(AppResult.Failure(DataError.NoNetwork), result)
        assertEquals(1, logger.errorMessages.size)
    }

    @Test
    fun `cancellation is rethrown`() = runTest {
        try {
            safeApiCall<String>(logger, "getCakes") { throw CancellationException() }
            fail("Expected CancellationException")
        } catch (e: CancellationException) {
            assertTrue(logger.errorMessages.isEmpty())
        }
    }
}
