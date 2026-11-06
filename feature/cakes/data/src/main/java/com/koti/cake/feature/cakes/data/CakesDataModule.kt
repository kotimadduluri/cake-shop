package com.koti.cake.feature.cakes.data

import com.koti.cake.core.network.createRetrofit
import com.koti.cake.feature.cakes.domain.CakeRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private const val BASE_URL = "https://raw.githubusercontent.com/Waracle/mobile-coding-test-api/refs/heads/main/"

fun cakesDataModule(): Module = module {
    single<CakeApi> { createRetrofit(BASE_URL, get(), get()).create(CakeApi::class.java) }
    singleOf(::CakeRepositoryImpl) { bind<CakeRepository>() }
}
