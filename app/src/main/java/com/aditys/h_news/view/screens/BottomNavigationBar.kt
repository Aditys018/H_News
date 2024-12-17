package com.aditys.h_news.view.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar {
        val backStackEntry = navController.currentBackStackEntryAsState()
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.name) },
                label = if (selected) { { Text(item.name) } } else { null },
                selected = selected,
                alwaysShowLabel = false,
                onClick = {
                    if (item.route == "home") {
                        navController.navigate("news") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    } else {
                        onItemClick(item)
                    }
                }
            )
        }
    }
}