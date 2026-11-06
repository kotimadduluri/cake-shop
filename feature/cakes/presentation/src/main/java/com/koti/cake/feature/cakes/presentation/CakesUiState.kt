package com.koti.cake.feature.cakes.presentation

import com.koti.cake.core.domain.DataError
import com.koti.cake.feature.cakes.domain.Cake

// Error shows as a snackbar if cakes are already listed, else as a full screen error.
internal data class CakesUiState(
    val cakes: List<Cake> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: DataError? = null,
)
