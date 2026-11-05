package com.koti.cake.core.network

import com.koti.cake.core.domain.DataError
import kotlinx.serialization.SerializationException
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class ThrowableToDataErrorTest {

    @Test
    fun `timeout maps to Timeout`() {
        assertEquals(DataError.Timeout, SocketTimeoutException().toDataError())
    }

    @Test
    fun `io error maps to NoNetwork`() {
        assertEquals(DataError.NoNetwork, IOException().toDataError())
    }

    @Test
    fun `5xx maps to Server`() {
        assertEquals(DataError.Server(503), httpException(503).toDataError())
    }

    @Test
    fun `4xx maps to Client`() {
        assertEquals(DataError.Client(404), httpException(404).toDataError())
    }

    @Test
    fun `bad json maps to Parse`() {
        assertEquals(DataError.Parse, SerializationException("bad").toDataError())
    }

    @Test
    fun `anything else maps to Unknown`() {
        assertEquals(DataError.Unknown, IllegalStateException().toDataError())
    }

    private fun httpException(code: Int) = HttpException(Response.error<Any>(code, "".toResponseBody()))
}
