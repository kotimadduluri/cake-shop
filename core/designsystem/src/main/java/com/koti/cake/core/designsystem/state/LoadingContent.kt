package com.koti.cake.core.designsystem.state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koti.cake.core.designsystem.R
import com.koti.cake.core.designsystem.list.appCardItems
import com.koti.cake.core.designsystem.theme.AppSpacing
import com.koti.cake.core.designsystem.theme.AppTheme

private val SkeletonTitleWidths = listOf(140, 180, 120, 200, 150, 110, 170)

// Skeleton rows in the same shape as the list, read out as a single "Loading".
@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    val label = stringResource(R.string.designsystem_loading)
    LazyColumn(
        modifier = modifier.clearAndSetSemantics { contentDescription = label },
        contentPadding = contentPadding,
        userScrollEnabled = false,
    ) {
        appCardItems(items = SkeletonTitleWidths.withIndex().toList(), key = { it.index }) { row ->
            SkeletonRow(titleWidth = row.value)
        }
    }
}

@Composable
private fun SkeletonRow(titleWidth: Int) {
    val placeholder = MaterialTheme.colorScheme.surfaceVariant
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 88.dp)
            .padding(horizontal = AppSpacing.md, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppSpacing.md),
    ) {
        Box(Modifier.size(64.dp).background(placeholder, MaterialTheme.shapes.medium))
        Box(Modifier.width(titleWidth.dp).height(14.dp).background(placeholder, CircleShape))
    }
}

@Preview
@Composable
private fun LoadingContentPreview() {
    AppTheme {
        LoadingContent(contentPadding = PaddingValues(horizontal = AppSpacing.md))
    }
}
