package com.koti.cake

import com.koti.cake.core.network.networkModule
import com.koti.cake.feature.cakes.data.cakesDataModule
import com.koti.cake.feature.cakes.presentation.cakesPresentationModule
import org.koin.core.module.Module

fun appModules(): List<Module> = listOf(
    networkModule(),
    cakesDataModule(),
    cakesPresentationModule(),
)
