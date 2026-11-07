package com.koti.cake.feature.cakes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.koti.cake.core.designsystem.icon.AppIcon
import com.koti.cake.core.designsystem.state.EmptyContent
import com.koti.cake.core.navigation.BottomSheetSceneStrategy
import com.koti.cake.core.navigation.ListDetail
import com.koti.cake.feature.cakes.domain.Cake

@Composable
fun CakesNavHost() {
    val backStack = rememberNavBackStack(CakeList)
    // Wide windows show the detail beside the list, narrow ones fall through to the bottom sheet.
    val listDetail = ListDetail.rememberSceneStrategy<NavKey>()
    val bottomSheet = remember { BottomSheetSceneStrategy<NavKey>() }
    // The placeholder only makes sense beside a list, not beside loading or an error.
    var listHasCakes by rememberSaveable { mutableStateOf(false) }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        sceneStrategies = listOf(listDetail, bottomSheet),
        entryProvider = entryProvider {
            entry<CakeList>(
                metadata = ListDetail.listPane {
                    if (listHasCakes) {
                        EmptyContent(
                            icon = AppIcon.Cake,
                            title = stringResource(R.string.cakes_select_title),
                            message = stringResource(R.string.cakes_select_message),
                        )
                    }
                },
            ) {
                val open = backStack.lastOrNull() as? CakeDetail
                CakeListScreen(
                    selectedCake = open?.let { Cake(it.title, it.description, it.imageUrl) },
                    onHasCakesChange = { listHasCakes = it },
                    onCakeClick = { cake ->
                        // Replace an open detail so back returns to the list, not the previous cake.
                        if (backStack.lastOrNull() is CakeDetail) backStack.removeLastOrNull()
                        backStack.add(CakeDetail(title = cake.title, description = cake.description, imageUrl = cake.imageUrl))
                    },
                )
            }
            entry<CakeDetail>(metadata = BottomSheetSceneStrategy.bottomSheet() + ListDetail.detailPane()) {
                CakeDetailSheet(detail = it)
            }
        },
    )
}
