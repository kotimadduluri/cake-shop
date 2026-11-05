package com.koti.cake.feature.cakes.data

import com.koti.cake.core.network.createRetrofit
import com.koti.cake.feature.cakes.domain.CakeRepository
import org.koin.dsl.module

private const val BASE_URL = "https://raw.githubusercontent.com/Waracle/mobile-coding-test-api/refs/heads/main/"

val cakesDataModule = module {
    single<CakeApi> { createRetrofit(BASE_URL, get(), get()).create(CakeApi::class.java) }
    single<CakeRepository> { CakeRepositoryImpl(get(), get()) }
}
