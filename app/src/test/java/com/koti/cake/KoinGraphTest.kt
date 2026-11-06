package com.koti.cake

import com.koti.cake.feature.cakes.domain.GetCakesUseCase
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.verify.verify

class KoinGraphTest {

    @Test
    fun `app modules pass verify`() {
        module { includes(appModules()) }.verify()
    }

    @Test
    fun `cakes use case resolves`() {
        val koin = koinApplication { modules(appModules()) }.koin
        koin.get<GetCakesUseCase>()
        koin.close()
    }
}
