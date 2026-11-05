package com.koti.cake.core.designsystem.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.koti.cake.core.designsystem.R
import com.koti.cake.core.designsystem.icon.AppIcon

// Shows a plain placeholder while loading and an image-off icon when the image fails.
@Composable
fun AppRemoteImage(
    url: String,
    shape: AppImageShape,
    modifier: Modifier = Modifier,
) {
    var state by remember(url) { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }
    val clipShape = when (shape) {
        AppImageShape.Thumbnail -> MaterialTheme.shapes.medium
        AppImageShape.Banner -> MaterialTheme.shapes.large
    }
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onState = { state = it },
            modifier = Modifier.matchParentSize(),
        )
        if (state is AsyncImagePainter.State.Error) {
            Icon(
                painter = painterResource(AppIcon.ImageOff.resId),
                contentDescription = stringResource(R.string.designsystem_image_unavailable),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
