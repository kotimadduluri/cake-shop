package com.koti.cake.core.designsystem.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

// Draws the items as one rounded card with dividers between them.
fun <T> LazyListScope.appCardItems(
    items: List<T>,
    key: (T) -> Any,
    itemContent: @Composable (T) -> Unit,
) {
    itemsIndexed(items, key = { _, item -> key(item) }) { index, item ->
        val isFirst = index == 0
        val isLast = index == items.lastIndex
        val noCorner = CornerSize(0)
        val card = MaterialTheme.shapes.large
        val shape = card.copy(
            topStart = if (isFirst) card.topStart else noCorner,
            topEnd = if (isFirst) card.topEnd else noCorner,
            bottomStart = if (isLast) card.bottomStart else noCorner,
            bottomEnd = if (isLast) card.bottomEnd else noCorner,
        )
        Column(
            modifier = Modifier
                .clip(shape)
                .background(MaterialTheme.colorScheme.surface),
        ) {
            itemContent(item)
            if (!isLast) AppDivider()
        }
    }
}
