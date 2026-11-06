package com.koti.cake.feature.cakes.presentation

import com.koti.cake.feature.cakes.domain.GetCakesUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun cakesPresentationModule(): Module = module {
    factoryOf(::GetCakesUseCase)
    viewModelOf(::CakesViewModel)
}
