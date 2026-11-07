package com.koti.cake.feature.cakes.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koti.cake.core.domain.DataError
import com.koti.cake.core.designsystem.icon.AppIcon
import com.koti.cake.core.designsystem.icon.AppIconButton
import com.koti.cake.core.designsystem.list.AppListItem
import com.koti.cake.core.designsystem.list.AppPullToRefresh
import com.koti.cake.core.designsystem.list.appCardItems
import com.koti.cake.core.designsystem.snackbar.AppSnackbarHost
import com.koti.cake.core.designsystem.state.EmptyContent
import com.koti.cake.core.designsystem.state.ErrorContent
import com.koti.cake.core.designsystem.state.LoadingContent
import com.koti.cake.core.designsystem.theme.AppSpacing
import com.koti.cake.core.designsystem.theme.AppTheme
import com.koti.cake.core.designsystem.topbar.AppTopBar
import com.koti.cake.feature.cakes.domain.Cake
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun CakeListScreen(
    onCakeClick: (Cake) -> Unit,
    selectedCake: Cake?,
    onHasCakesChange: (Boolean) -> Unit,
    viewModel: CakesViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val hasCakes = state.cakes.isNotEmpty()
    LaunchedEffect(hasCakes) { onHasCakesChange(hasCakes) }
    CakeListContent(state = state, onAction = viewModel::onAction, onCakeClick = onCakeClick, selectedCake = selectedCake)
}

@Composable
internal fun CakeListContent(
    state: CakesUiState,
    onAction: (CakesAction) -> Unit,
    onCakeClick: (Cake) -> Unit,
    selectedCake: Cake? = null,
) {
    val listPadding = PaddingValues(
        start = AppSpacing.md,
        end = AppSpacing.md,
        bottom = AppSpacing.md + WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppTopBar(
                title = stringResource(R.string.cakes_title),
                accentTitle = stringResource(R.string.cakes_title_accent),
            ) {
                if (state.cakes.isNotEmpty() || (!state.isLoading && state.error == null)) {
                    AppIconButton(
                        icon = AppIcon.Refresh,
                        contentDescription = stringResource(R.string.cakes_refresh),
                        onClick = { onAction(CakesAction.Refresh) },
                    )
                }
            }
            AppPullToRefresh(
                isRefreshing = state.isRefreshing,
                onRefresh = { onAction(CakesAction.Refresh) },
                modifier = Modifier.fillMaxSize(),
            ) {
                val error = state.error
                when {
                    state.cakes.isNotEmpty() -> LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = listPadding,
                    ) {
                        // TODO: key by id if the API adds one. Cakes are only unique as a whole, two can share a title.
                        appCardItems(items = state.cakes, key = { it.toString() }) { cake ->
                            AppListItem(
                                title = cake.title,
                                imageUrl = cake.imageUrl,
                                onClick = { onCakeClick(cake) },
                                selected = cake == selectedCake,
                            )
                        }
                    }
                    state.isLoading -> LoadingContent(contentPadding = listPadding)
                    error != null -> ErrorContent(
                        icon = AppIcon.WifiOff,
                        title = stringResource(R.string.cakes_error_title),
                        message = stringResource(error.messageRes()),
                        retryLabel = stringResource(R.string.cakes_retry),
                        onRetry = { onAction(CakesAction.Retry) },
                    )
                    else -> EmptyContent(
                        icon = AppIcon.Cake,
                        title = stringResource(R.string.cakes_empty_title),
                        message = stringResource(R.string.cakes_empty_message),
                    )
                }
            }
        }
        AppSnackbarHost(
            message = if (state.cakes.isNotEmpty() && state.error != null) {
                stringResource(R.string.cakes_refresh_failed)
            } else {
                null
            },
            onShown = { onAction(CakesAction.ErrorShown) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(),
        )
    }
}

private val previewCakes = listOf(
    Cake("Banana Cake", "Donkey kongs favourite", ""),
    Cake("Lemon Cheesecake", "A cheesecake made of lemon", ""),
    Cake("Victoria Sponge", "Sponge with jam", ""),
)

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CakeListPreview() {
    AppTheme {
        CakeListContent(state = CakesUiState(cakes = previewCakes), onAction = {}, onCakeClick = {})
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CakeListErrorPreview() {
    AppTheme {
        CakeListContent(state = CakesUiState(error = DataError.NoNetwork), onAction = {}, onCakeClick = {})
    }
}
