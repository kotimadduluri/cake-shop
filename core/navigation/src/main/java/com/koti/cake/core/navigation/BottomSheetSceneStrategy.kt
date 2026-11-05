package com.koti.cake.core.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.contains
import androidx.navigation3.runtime.metadata
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope

// Shows entries marked with bottomSheet() in a bottom sheet. Other entries are left to the next strategy.
class BottomSheetSceneStrategy<T : Any> : SceneStrategy<T> {

    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
        val lastEntry = entries.lastOrNull() ?: return null
        if (BottomSheetKey !in lastEntry.metadata) return null
        return BottomSheetScene(
            key = lastEntry.contentKey,
            previousEntries = entries.dropLast(1),
            overlaidEntries = entries.dropLast(1),
            entry = lastEntry,
            onBack = onBack,
        )
    }

    companion object {
        fun bottomSheet(): Map<String, Any> = metadata { put(BottomSheetKey, true) }
    }
}
