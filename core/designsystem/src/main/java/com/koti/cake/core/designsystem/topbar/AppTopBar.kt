package com.koti.cake.core.designsystem.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.koti.cake.core.designsystem.icon.AppIcon
import com.koti.cake.core.designsystem.icon.AppIconButton
import com.koti.cake.core.designsystem.theme.AppSpacing
import com.koti.cake.core.designsystem.theme.AppTheme

// The title is shown in two parts, the second part in the accent colour.
@Composable
fun AppTopBar(
    title: String,
    accentTitle: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val accent = MaterialTheme.colorScheme.primary
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = AppSpacing.lg, end = AppSpacing.md, top = AppSpacing.md, bottom = AppSpacing.md),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = buildAnnotatedString {
                append(title)
                append(" ")
                withStyle(SpanStyle(color = accent)) { append(accentTitle) }
            },
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .weight(1f)
                .semantics { heading() },
        )
        Row(content = actions)
    }
}

@Preview
@Composable
private fun AppTopBarPreview() {
    AppTheme {
        AppTopBar(title = "Cake", accentTitle = "Shop") {
            AppIconButton(icon = AppIcon.Refresh, contentDescription = "Refresh", onClick = {})
        }
    }
}
