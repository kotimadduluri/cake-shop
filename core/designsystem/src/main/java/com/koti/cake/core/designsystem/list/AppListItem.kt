package com.koti.cake.core.designsystem.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koti.cake.core.designsystem.image.AppImageShape
import com.koti.cake.core.designsystem.image.AppRemoteImage
import com.koti.cake.core.designsystem.text.AppText
import com.koti.cake.core.designsystem.text.AppTextStyle
import com.koti.cake.core.designsystem.theme.AppSpacing
import com.koti.cake.core.designsystem.theme.AppTheme

@Composable
fun AppListItem(
    title: String,
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 88.dp)
            .background(if (selected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent)
            .semantics { this.selected = selected }
            .clickable(onClick = onClick)
            .padding(horizontal = AppSpacing.md, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppSpacing.md),
    ) {
        AppRemoteImage(url = imageUrl, shape = AppImageShape.Thumbnail, modifier = Modifier.size(64.dp))
        AppText(text = title, style = AppTextStyle.ItemTitle, modifier = Modifier.weight(1f))
    }
}

@Preview
@Preview(fontScale = 2f)
@Composable
private fun AppListItemPreview() {
    AppTheme {
        AppListItem(title = "Chocolate Brownies", imageUrl = "", onClick = {})
    }
}
