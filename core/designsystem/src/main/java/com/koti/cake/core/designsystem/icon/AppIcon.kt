package com.koti.cake.core.designsystem.icon

import androidx.annotation.DrawableRes
import com.koti.cake.core.designsystem.R

enum class AppIcon(@param:DrawableRes internal val resId: Int) {
    Refresh(R.drawable.ic_refresh),
    ImageOff(R.drawable.ic_image_off),
    WifiOff(R.drawable.ic_wifi_off),
    Cake(R.drawable.ic_cake),
}
