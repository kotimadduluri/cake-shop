package com.koti.cake.feature.cakes.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CakeDto(
    @SerialName("title") val title: String,
    @SerialName("desc") val desc: String,
    @SerialName("image") val image: String,
)
