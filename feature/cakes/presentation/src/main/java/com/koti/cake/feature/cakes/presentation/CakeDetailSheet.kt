package com.koti.cake.feature.cakes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koti.cake.core.designsystem.image.AppImageShape
import com.koti.cake.core.designsystem.image.AppRemoteImage
import com.koti.cake.core.designsystem.text.AppText
import com.koti.cake.core.designsystem.text.AppTextStyle
import com.koti.cake.core.designsystem.theme.AppSpacing
import com.koti.cake.core.designsystem.theme.AppTheme

@Composable
internal fun CakeDetailSheet(
    detail: CakeDetail,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            // Needed in the side pane; the sheet has already consumed these insets.
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top + WindowInsetsSides.End))
            .verticalScroll(rememberScrollState())
            .padding(start = AppSpacing.md, end = AppSpacing.md, bottom = AppSpacing.xl),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.md),
    ) {
        AppRemoteImage(
            url = detail.imageUrl,
            shape = AppImageShape.Banner,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        )
        Column(verticalArrangement = Arrangement.spacedBy(AppSpacing.sm)) {
            AppText(text = detail.title, style = AppTextStyle.Title)
            AppText(text = detail.description, style = AppTextStyle.BodySecondary)
        }
    }
}

@Preview
@Preview(fontScale = 2f)
@Composable
private fun CakeDetailSheetPreview() {
    AppTheme {
        CakeDetailSheet(
            detail = CakeDetail(title = "Carrot Cake", description = "Bugs bunnys favourite", imageUrl = ""),
        )
    }
}
