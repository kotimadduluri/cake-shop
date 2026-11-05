package com.koti.cake.feature.cakes.data

import retrofit2.http.GET

internal interface CakeApi {
    @GET("cakes")
    suspend fun getCakes(): List<CakeDto>
}
