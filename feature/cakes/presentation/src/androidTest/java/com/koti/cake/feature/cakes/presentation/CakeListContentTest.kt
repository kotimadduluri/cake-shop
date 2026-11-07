package com.koti.cake.feature.cakes.presentation

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.koti.cake.core.designsystem.theme.AppTheme
import com.koti.cake.core.domain.DataError
import com.koti.cake.feature.cakes.domain.Cake
import com.koti.cake.feature.cakes.domain.TestCakes.banana
import com.koti.cake.feature.cakes.domain.TestCakes.lemon
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CakeListContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    private fun string(@StringRes id: Int) = ApplicationProvider.getApplicationContext<Context>().getString(id)

    private fun setContent(
        state: CakesUiState,
        onAction: (CakesAction) -> Unit = {},
        onCakeClick: (Cake) -> Unit = {},
    ) {
        composeRule.setContent {
            AppTheme {
                CakeListContent(state = state, onAction = onAction, onCakeClick = onCakeClick)
            }
        }
    }

    @Test
    fun showsEveryCakeTitle() {
        // Given
        setContent(CakesUiState(cakes = listOf(banana, lemon)))

        // Then
        composeRule.onNodeWithText(banana.title).assertIsDisplayed()
        composeRule.onNodeWithText(lemon.title).assertIsDisplayed()
    }

    @Test
    fun tappingRetryOnTheErrorSendsRetry() {
        // Given
        val actions = mutableListOf<CakesAction>()
        setContent(CakesUiState(error = DataError.NoNetwork), onAction = { actions += it })
        composeRule.onNodeWithText(string(R.string.cakes_error_title)).assertIsDisplayed()

        // When
        composeRule.onNodeWithText(string(R.string.cakes_retry)).performClick()

        // Then
        assertEquals(listOf(CakesAction.Retry), actions)
    }

    @Test
    fun tappingACakePassesThatCake() {
        // Given
        var clicked: Cake? = null
        setContent(CakesUiState(cakes = listOf(banana, lemon)), onCakeClick = { clicked = it })

        // When
        composeRule.onNodeWithText(lemon.title).performClick()

        // Then
        assertEquals(lemon, clicked)
    }
}
