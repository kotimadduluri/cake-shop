package com.koti.cake.feature.cakes.presentation

import com.koti.cake.core.domain.AppResult
import com.koti.cake.feature.cakes.domain.Cake
import com.koti.cake.feature.cakes.domain.CakeRepository
import kotlinx.coroutines.CompletableDeferred

class FakeCakeRepository(var result: AppResult<List<Cake>>) : CakeRepository {
    var calls = 0
        private set

    // Holds getCakes until completed.
    var gate: CompletableDeferred<Unit>? = null

    override suspend fun getCakes(): AppResult<List<Cake>> {
        calls++
        gate?.await()
        return result
    }
}
