package com.koti.cake.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.OverlayScene
import com.koti.cake.core.designsystem.sheet.AppModalBottomSheet
import com.koti.cake.core.designsystem.sheet.AppSheetState
import com.koti.cake.core.designsystem.sheet.rememberAppSheetState

// Shows the top entry in a bottom sheet, on top of the entries below it.
internal data class BottomSheetScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    override val overlaidEntries: List<NavEntry<T>>,
    private val entry: NavEntry<T>,
    private val onBack: () -> Unit,
) : OverlayScene<T> {

    override val entries: List<NavEntry<T>> = listOf(entry)

    private var sheetState: AppSheetState? = null

    override val content: @Composable () -> Unit = {
        val state = rememberAppSheetState()
        sheetState = state
        AppModalBottomSheet(onDismissRequest = onBack, sheetState = state) {
            entry.Content()
        }
    }

    // Plays the slide down animation when the entry is removed from the back stack by code.
    override suspend fun onRemove() {
        sheetState?.hide()
    }
}
