package com.aditys.h_news.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import android.os.Build
import android.view.WindowInsetsController
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowInsetsControllerCompat
import com.aditys.h_news.theme.H_NewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make content draw behind the system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Set status bar transparent
        window.statusBarColor = android.graphics.Color.TRANSPARENT

        // Optional: Set light or dark icons for the status bar
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false // false = white icons

        setContent {
            H_NewsTheme {
                AppNavigation()
            }
        }
    }
}
