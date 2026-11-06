package com.koti.cake.core.designsystem.snackbar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier

// Shows the message once, then calls onShown so the caller can clear it.
@Composable
fun AppSnackbarHost(
    message: String?,
    onShown: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val hostState = remember { SnackbarHostState() }
    val currentOnShown by rememberUpdatedState(onShown)
    LaunchedEffect(message) {
        if (message != null) {
            hostState.showSnackbar(message)
            currentOnShown()
        }
    }
    SnackbarHost(hostState = hostState, modifier = modifier)
}
