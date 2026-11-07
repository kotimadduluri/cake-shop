package com.koti.cake.feature.cakes.data

import com.koti.cake.core.domain.AppResult
import com.koti.cake.core.network.Logger
import com.koti.cake.core.network.safeApiCall
import com.koti.cake.feature.cakes.domain.Cake
import com.koti.cake.feature.cakes.domain.CakeRepository

internal class CakeRepositoryImpl(
    private val api: CakeApi,
    private val logger: Logger,
) : CakeRepository {

    // TODO: cache in Room so the list shows offline and right away after process death.
    override suspend fun getCakes(): AppResult<List<Cake>> =
        safeApiCall(logger, "getCakes") { api.getCakes().map { it.toCake() } }
}
