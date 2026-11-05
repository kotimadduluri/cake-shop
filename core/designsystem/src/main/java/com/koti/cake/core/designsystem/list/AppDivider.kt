package com.koti.cake.core.designsystem.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Starts after the row image: 16 padding + 64 image + 16 gap.
private val DividerInset = 96.dp

@Composable
internal fun AppDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier.padding(start = DividerInset),
        color = MaterialTheme.colorScheme.outlineVariant,
    )
}
