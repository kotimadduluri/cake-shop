package com.koti.cake.feature.cakes.presentation

import com.koti.cake.core.domain.AppResult
import com.koti.cake.core.domain.DataError
import com.koti.cake.feature.cakes.domain.Cake
import com.koti.cake.feature.cakes.domain.GetCakesUseCase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CakesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val lemon = Cake("Lemon Cheesecake", "A cheesecake made of lemon", "https://example.com/lemon.jpg")
    private val banana = Cake("Banana Cake", "Donkey kongs favourite", "https://example.com/banana.jpg")

    private fun TestScope.collect(viewModel: CakesViewModel) =
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) { viewModel.uiState.collect() }

    @Test
    fun `loading is shown while the first load runs`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Success(listOf(banana)))
        repository.gate = CompletableDeferred()
        val viewModel = CakesViewModel(GetCakesUseCase(repository))

        // When
        collect(viewModel)

        // Then
        assertEquals(CakesUiState(isLoading = true), viewModel.uiState.value)
    }

    @Test
    fun `successful load shows cakes without duplicates sorted by title`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Success(listOf(lemon, banana, lemon)))
        val viewModel = CakesViewModel(GetCakesUseCase(repository))

        // When
        collect(viewModel)

        // Then
        assertEquals(CakesUiState(cakes = listOf(banana, lemon)), viewModel.uiState.value)
    }

    @Test
    fun `failed load shows the error`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Failure(DataError.NoNetwork))
        val viewModel = CakesViewModel(GetCakesUseCase(repository))

        // When
        collect(viewModel)

        // Then
        assertEquals(CakesUiState(error = DataError.NoNetwork), viewModel.uiState.value)
    }

    @Test
    fun `refreshing is shown while a refresh runs`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Success(listOf(banana)))
        val viewModel = CakesViewModel(GetCakesUseCase(repository))
        collect(viewModel)
        repository.gate = CompletableDeferred()

        // When
        viewModel.onAction(CakesAction.Refresh)

        // Then
        assertEquals(CakesUiState(cakes = listOf(banana), isRefreshing = true), viewModel.uiState.value)
    }

    @Test
    fun `refresh while a load is running does not load again`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Success(listOf(banana)))
        repository.gate = CompletableDeferred()
        val viewModel = CakesViewModel(GetCakesUseCase(repository))
        collect(viewModel)

        // When
        viewModel.onAction(CakesAction.Refresh)

        // Then
        assertEquals(1, repository.calls)
        assertEquals(CakesUiState(isLoading = true), viewModel.uiState.value)
    }

    @Test
    fun `failed refresh keeps the cakes and shows the error`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Success(listOf(banana)))
        val viewModel = CakesViewModel(GetCakesUseCase(repository))
        collect(viewModel)
        repository.result = AppResult.Failure(DataError.Timeout)

        // When
        viewModel.onAction(CakesAction.Refresh)

        // Then
        assertEquals(CakesUiState(cakes = listOf(banana), error = DataError.Timeout), viewModel.uiState.value)
    }

    @Test
    fun `error shown clears the error`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Failure(DataError.NoNetwork))
        val viewModel = CakesViewModel(GetCakesUseCase(repository))
        collect(viewModel)

        // When
        viewModel.onAction(CakesAction.ErrorShown)

        // Then
        assertNull(viewModel.uiState.value.error)
    }

    @Test
    fun `retry after a failure loads the cakes`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Failure(DataError.NoNetwork))
        val viewModel = CakesViewModel(GetCakesUseCase(repository))
        collect(viewModel)
        repository.result = AppResult.Success(listOf(banana))

        // When
        viewModel.onAction(CakesAction.Retry)

        // Then
        assertEquals(CakesUiState(cakes = listOf(banana)), viewModel.uiState.value)
    }

    @Test
    fun `collecting again later does not load again`() = runTest {
        // Given
        val repository = FakeCakeRepository(AppResult.Success(listOf(banana)))
        val viewModel = CakesViewModel(GetCakesUseCase(repository))
        collect(viewModel).cancel()
        advanceTimeBy(6_000)

        // When
        collect(viewModel)

        // Then
        assertEquals(1, repository.calls)
    }
}
