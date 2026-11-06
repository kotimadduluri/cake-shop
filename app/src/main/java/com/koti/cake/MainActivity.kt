package com.koti.cake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.koti.cake.core.designsystem.theme.AppTheme
import com.koti.cake.feature.cakes.presentation.CakesGraph
import com.koti.cake.feature.cakes.presentation.CakesNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val backStack = rememberNavBackStack(CakesGraph)
                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        entry<CakesGraph> { CakesNavHost() }
                    },
                )
            }
        }
    }
}
