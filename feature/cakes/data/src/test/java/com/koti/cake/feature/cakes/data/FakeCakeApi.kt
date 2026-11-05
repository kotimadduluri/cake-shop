package com.koti.cake.feature.cakes.data

internal class FakeCakeApi(private val response: () -> List<CakeDto>) : CakeApi {
    override suspend fun getCakes(): List<CakeDto> = response()
}
