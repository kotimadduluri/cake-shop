package com.koti.cake.feature.cakes.domain

import com.koti.cake.core.domain.AppResult
import com.koti.cake.core.domain.DataError
import com.koti.cake.feature.cakes.domain.TestCakes.banana
import com.koti.cake.feature.cakes.domain.TestCakes.carrot
import com.koti.cake.feature.cakes.domain.TestCakes.lemon
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCakesUseCaseTest {

    @Test
    fun `success removes duplicates and sorts by title`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Success(listOf(lemon, banana, lemon, carrot, banana)))

        // When
        val result = GetCakesUseCase(repository)()

        // Then
        assertEquals(AppResult.Success(listOf(banana, carrot, lemon)), result)
    }

    @Test
    fun `sort ignores letter case`() = runTest {
        // Given
        val lowerCase = banana.copy(title = "apple cake")
        val repository = FakeCakeRepository(AppResult.Success(listOf(carrot, lowerCase)))

        // When
        val result = GetCakesUseCase(repository)()

        // Then
        assertEquals(AppResult.Success(listOf(lowerCase, carrot)), result)
    }

    @Test
    fun `failure is passed through`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Failure(DataError.NoNetwork))

        // When
        val result = GetCakesUseCase(repository)()

        // Then
        assertEquals(AppResult.Failure(DataError.NoNetwork), result)
    }
}
