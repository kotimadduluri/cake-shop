package com.koti.cake.core.designsystem.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.koti.cake.core.designsystem.icon.AppIcon
import com.koti.cake.core.designsystem.theme.AppTheme

@Composable
fun EmptyContent(
    icon: AppIcon,
    title: String,
    message: String,
    modifier: Modifier = Modifier,
) {
    StatusContent(icon = icon, title = title, message = message, modifier = modifier)
}

@Preview
@Composable
private fun EmptyContentPreview() {
    AppTheme {
        EmptyContent(icon = AppIcon.Cake, title = "No cakes right now", message = "Pull down or tap refresh to check again.")
    }
}
