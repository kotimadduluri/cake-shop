package com.koti.cake.core.designsystem.state

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koti.cake.core.designsystem.icon.AppIcon
import com.koti.cake.core.designsystem.theme.AppTheme

@Composable
fun ErrorContent(
    icon: AppIcon,
    title: String,
    message: String,
    retryLabel: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    StatusContent(
        icon = icon,
        title = title,
        message = message,
        modifier = modifier.semantics { liveRegion = LiveRegionMode.Polite },
    ) {
        Button(
            onClick = onRetry,
            modifier = Modifier.heightIn(min = 48.dp),
            contentPadding = PaddingValues(horizontal = 28.dp),
        ) {
            Text(text = retryLabel)
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorContentPreview() {
    AppTheme {
        ErrorContent(
            icon = AppIcon.WifiOff,
            title = "Couldn't load cakes",
            message = "Check your internet connection and try again.",
            retryLabel = "Retry",
            onRetry = {},
        )
    }
}
