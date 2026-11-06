package com.koti.cake.feature.cakes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koti.cake.core.domain.AppResult
import com.koti.cake.feature.cakes.domain.GetCakesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CakesViewModel(private val getCakes: GetCakesUseCase) : ViewModel() {

    private val state = MutableStateFlow(CakesUiState(isLoading = true))
    private var loadJob: Job? = null

    // First load runs once, when the screen starts collecting.
    val uiState: StateFlow<CakesUiState> = state
        .onStart { if (loadJob == null) load() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), state.value)

    fun onAction(action: CakesAction) {
        when (action) {
            CakesAction.Retry -> load { it.copy(isLoading = true, error = null) }
            CakesAction.Refresh -> load { it.copy(isRefreshing = true) }
            CakesAction.ErrorShown -> state.update { it.copy(error = null) }
        }
    }

    private fun load(onStart: (CakesUiState) -> CakesUiState = { it }) {
        if (loadJob?.isActive == true) return
        state.update(onStart)
        loadJob = viewModelScope.launch {
            val result = getCakes()
            state.update { current ->
                when (result) {
                    is AppResult.Success -> current.copy(
                        cakes = result.data,
                        isLoading = false,
                        isRefreshing = false,
                        error = null,
                    )
                    is AppResult.Failure -> current.copy(
                        isLoading = false,
                        isRefreshing = false,
                        error = result.error,
                    )
                }
            }
        }
    }
}
