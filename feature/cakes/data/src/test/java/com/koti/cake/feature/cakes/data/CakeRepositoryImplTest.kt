package com.koti.cake.feature.cakes.data

import com.koti.cake.core.domain.AppResult
import com.koti.cake.core.domain.DataError
import com.koti.cake.feature.cakes.domain.Cake
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class CakeRepositoryImplTest {

    @Test
    fun `success maps each dto to a cake`() = runTest {
        // Given
        val dto = CakeDto("Carrot Cake", "Bugs bunnys favourite", "https://example.com/carrot.jpg")
        val repository = CakeRepositoryImpl(FakeCakeApi { listOf(dto) }, FakeLogger())

        // When
        val result = repository.getCakes()

        // Then
        val expected = Cake("Carrot Cake", "Bugs bunnys favourite", "https://example.com/carrot.jpg")
        assertEquals(AppResult.Success(listOf(expected)), result)
    }

    @Test
    fun `network failure returns no network error`() = runTest {
        // Given
        val repository = CakeRepositoryImpl(FakeCakeApi { throw IOException("offline") }, FakeLogger())

        // When
        val result = repository.getCakes()

        // Then
        assertEquals(AppResult.Failure(DataError.NoNetwork), result)
    }
}
