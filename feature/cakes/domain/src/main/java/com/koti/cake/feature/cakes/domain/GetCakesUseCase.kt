package com.koti.cake.feature.cakes.domain

import com.koti.cake.core.domain.AppResult
import com.koti.cake.core.domain.map

// Returns the cakes without duplicates, sorted by title.
class GetCakesUseCase(private val repository: CakeRepository) {

    suspend operator fun invoke(): AppResult<List<Cake>> =
        repository.getCakes().map { cakes ->
            cakes.distinct().sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.title })
        }
}
