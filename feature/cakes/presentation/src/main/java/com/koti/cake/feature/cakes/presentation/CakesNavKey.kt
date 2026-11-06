package com.koti.cake.feature.cakes.presentation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object CakesGraph : NavKey

@Serializable
internal data object CakeList : NavKey
