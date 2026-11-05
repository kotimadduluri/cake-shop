package com.koti.cake.core.designsystem.sheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState

// Wraps the Material sheet state so callers can hide the sheet without using Material directly.
@OptIn(ExperimentalMaterial3Api::class)
class AppSheetState internal constructor(internal val state: SheetState) {
    suspend fun hide() = state.hide()
}
