package com.koti.cake.core.designsystem.list

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

// Draws the items as one rounded card with dividers between them, fading each one in.
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
        // Saved so an item fades in once, not again on scroll back or rotation.
        var shown by rememberSaveable { mutableStateOf(false) }
        LaunchedEffect(Unit) { shown = true }
        val progress by animateFloatAsState(
            targetValue = if (shown) 1f else 0f,
            animationSpec = tween(durationMillis = 300, delayMillis = minOf(index, 5) * 40),
        )
        Column(
            modifier = Modifier
                .animateItem(fadeInSpec = null)
                .graphicsLayer {
                    alpha = progress
                    translationY = (1f - progress) * -24.dp.toPx()
                }
                .clip(shape)
                .background(MaterialTheme.colorScheme.surface),
        ) {
            itemContent(item)
            if (!isLast) AppDivider()
        }
    }
}
