package com.koti.cake.core.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirectiveWithTwoPanesOnMediumWidth
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.navigation3.scene.SceneStrategy

// Thin wrapper over the Material list-detail strategy so features don't depend on Material.
// Two panes from medium width so phones in landscape get them too.
// On compact windows it hands over to the next strategy.
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
object ListDetail {
    @Composable
    fun <T : Any> rememberSceneStrategy(): SceneStrategy<T> = rememberListDetailSceneStrategy(
        // The default also pops the list, as list plus placeholder counts as the same layout.
        backNavigationBehavior = BackNavigationBehavior.PopLatest,
        directive = calculatePaneScaffoldDirectiveWithTwoPanesOnMediumWidth(currentWindowAdaptiveInfoV2()),
    )

    fun listPane(placeholder: @Composable () -> Unit): Map<String, Any> =
        ListDetailSceneStrategy.listPane(detailPlaceholder = { placeholder() })

    fun detailPane(): Map<String, Any> = ListDetailSceneStrategy.detailPane()
}
