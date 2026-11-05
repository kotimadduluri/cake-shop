package com.koti.cake.core.designsystem.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AppText(
    text: String,
    style: AppTextStyle,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    val typography = MaterialTheme.typography
    val textStyle = when (style) {
        AppTextStyle.Headline -> typography.headlineMedium
        AppTextStyle.Title -> typography.titleLarge
        AppTextStyle.ItemTitle -> typography.titleMedium
        AppTextStyle.Body, AppTextStyle.BodySecondary -> typography.bodyLarge
    }
    val color = if (style == AppTextStyle.BodySecondary) MaterialTheme.colorScheme.onSurfaceVariant else Color.Unspecified
    Text(text = text, style = textStyle, color = color, textAlign = textAlign, modifier = modifier)
}
