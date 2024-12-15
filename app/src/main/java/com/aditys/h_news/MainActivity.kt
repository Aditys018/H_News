package com.aditys.h_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aditys.h_news.ui.presentation.onboarding.OnBoardingScreen
import com.aditys.h_news.theme.H_NewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()
        setContent {
            H_NewsTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    OnBoardingScreen()

                }
            }
        }
    }
}

