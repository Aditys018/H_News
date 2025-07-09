package com.aditys.h_news.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aditys.h_news.view.screens.*
import com.aditys.h_news.R
import com.aditys.h_news.view.screens.BottomNavItem

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Jobs,
        BottomNavItem.Settings
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                navController = navController,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(onPostClick = { /* handle post click */ }) }
            composable("search") { SearchScreen(onPostClick = { /* handle post click */ }) }
            composable("jobs") { JobsScreen(onJobClick = { /* handle job click */ }) }
            composable("settings") { SettingsScreen(navController) }
            // Add other screens as needed
        }
    }
}
