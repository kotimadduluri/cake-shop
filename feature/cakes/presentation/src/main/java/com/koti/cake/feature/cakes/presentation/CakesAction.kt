package com.koti.cake.feature.cakes.presentation

internal sealed interface CakesAction {
    data object Retry : CakesAction
    data object Refresh : CakesAction
    data object ErrorShown : CakesAction
}
