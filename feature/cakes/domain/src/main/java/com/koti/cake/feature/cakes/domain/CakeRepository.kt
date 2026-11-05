package com.koti.cake.feature.cakes.domain

import com.koti.cake.core.domain.AppResult

interface CakeRepository {
    suspend fun getCakes(): AppResult<List<Cake>>
}
