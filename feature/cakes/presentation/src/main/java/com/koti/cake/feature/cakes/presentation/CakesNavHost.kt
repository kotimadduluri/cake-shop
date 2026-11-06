package com.koti.cake.feature.cakes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.koti.cake.core.navigation.BottomSheetSceneStrategy

@Composable
fun CakesNavHost() {
    val backStack = rememberNavBackStack(CakeList)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        sceneStrategies = remember { listOf(BottomSheetSceneStrategy()) },
        entryProvider = entryProvider {
            entry<CakeList> {
                CakeListScreen(
                    onCakeClick = { cake ->
                        backStack.add(CakeDetail(title = cake.title, description = cake.description, imageUrl = cake.imageUrl))
                    },
                )
            }
            entry<CakeDetail>(metadata = BottomSheetSceneStrategy.bottomSheet()) { CakeDetailSheet(detail = it) }
        },
    )
}
