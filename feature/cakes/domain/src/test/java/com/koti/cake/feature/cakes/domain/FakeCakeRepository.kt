package com.koti.cake.feature.cakes.domain

import com.koti.cake.core.domain.AppResult

class FakeCakeRepository(var result: AppResult<List<Cake>>) : CakeRepository {
    override suspend fun getCakes(): AppResult<List<Cake>> = result
}
