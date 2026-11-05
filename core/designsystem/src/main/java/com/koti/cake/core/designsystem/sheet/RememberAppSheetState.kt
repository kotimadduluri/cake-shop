package com.koti.cake.core.designsystem.sheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberAppSheetState(): AppSheetState {
    val state = rememberModalBottomSheetState()
    return remember(state) { AppSheetState(state) }
}
