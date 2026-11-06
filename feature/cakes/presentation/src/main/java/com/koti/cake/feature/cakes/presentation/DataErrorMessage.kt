package com.koti.cake.feature.cakes.presentation

import androidx.annotation.StringRes
import com.koti.cake.core.domain.DataError

@StringRes
internal fun DataError.messageRes(): Int = when (this) {
    DataError.NoNetwork, DataError.Timeout -> R.string.cakes_error_no_network
    else -> R.string.cakes_error_generic
}
