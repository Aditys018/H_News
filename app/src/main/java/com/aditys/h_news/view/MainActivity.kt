package com.aditys.h_news.view

import com.aditys.h_news.view.screens.SettingsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aditys.h_news.theme.H_NewsTheme
import com.aditys.h_news.view.presentation.onboarding.OnBoardingScreen
import com.aditys.h_news.view.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            H_NewsTheme {
                val navController = rememberNavController()
                var showBottomBar by remember { mutableStateOf(false) }

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "onboarding",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen() }
                        composable("search") { SearchScreen() }
                        composable("profile") { ProfileScreen(navController) }
                        composable("settings") { SettingsScreen(navController) }
                        composable("bookmarks") { BookmarksScreen() }
                        composable("jobs") { JobsScreen() }
                        composable("onboarding") {
                            OnBoardingScreen(navController) {
                                showBottomBar = true
                            }
                        }
                    }
                }
            }
        }
    }
}