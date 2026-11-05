package com.koti.cake.feature.cakes.data

import com.koti.cake.feature.cakes.domain.Cake

internal fun CakeDto.toCake(): Cake = Cake(title = title, description = desc, imageUrl = image)
