package com.aditys.h_news.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aditys.h_news.view.screens.*
import com.aditys.h_news.view.screens.BottomNavItem
import androidx.navigation.NavType
import androidx.navigation.navArgument
import android.net.Uri

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
            composable("home") {
                HomeScreen(
                    onPostClick = { item ->
                        navController.navigate(
                            "newsDetail/${Uri.encode(item.title ?: "")}/${Uri.encode(item.author ?: "")}/${
                                Uri.encode(
                                    item.story_text ?: ""
                                )
                            }/${Uri.encode(item.url ?: "")}"
                        )
                    },
                    navController = navController
                )
            }
            composable("search") {
                SearchScreen(onPostClick = { item ->
                    navController.navigate(
                        "newsDetail/${Uri.encode(item.title ?: "")}/${Uri.encode(item.author ?: "")}/${
                            Uri.encode(
                                item.storyText ?: ""
                            )
                        }/${Uri.encode(item.url ?: "")}"
                    )
                })
            }
            composable("jobs") {
                JobsScreen(onJobClick = { job ->
                    navController.navigate(
                        "newsDetail/${Uri.encode(job.title ?: "")}/${Uri.encode(job.author ?: "")}/${
                            Uri.encode(
                                job.createdAt ?: ""
                            )
                        }/${Uri.encode(job.url ?: "")}"
                    )
                })
            }
            composable("settings") { SettingsScreen(navController) }
            composable(
                "newsDetail/{title}/{author}/{content}/{url}",
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType },
                    navArgument("author") { type = NavType.StringType },
                    navArgument("content") { type = NavType.StringType },
                    navArgument("url") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                NewsDetailScreen(
                    title = backStackEntry.arguments?.getString("title") ?: "",
                    author = backStackEntry.arguments?.getString("author") ?: "",
                    content = backStackEntry.arguments?.getString("content") ?: "",
                    url = backStackEntry.arguments?.getString("url"),
                    onBack = { navController.popBackStack() }
                )
            }
            // Add other screens as needed
        }
    }
}
