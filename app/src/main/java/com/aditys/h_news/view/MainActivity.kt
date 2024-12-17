package com.aditys.h_news.view

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
import com.aditys.h_news.R
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
                val items = listOf(
                    BottomNavItem("Home", "home", R.drawable.house),
                    BottomNavItem("Search", "search", R.drawable.search),
                    BottomNavItem("Profile", "profile", R.drawable.person),
                    BottomNavItem("Bookmarks", "bookmarks", R.drawable.bookmarks),
                    BottomNavItem("Jobs", "jobs", R.drawable.job)
                )

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(items = items, navController = navController) {
                                navController.navigate(it.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "onboarding", modifier = Modifier.padding(innerPadding)) {
                        composable("home") { NewsScreen() }
                        composable("search") { SearchScreen() }
                        composable("profile") { ProfileScreen(navController) }
                        composable("bookmarks") { BookmarksScreen() }
                        composable("jobs") { JobsScreen() }
                        composable("news") { NewsScreen() }
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