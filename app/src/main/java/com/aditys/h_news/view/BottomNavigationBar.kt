package com.aditys.h_news.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aditys.h_news.R


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Search,
        BottomNavItem.Jobs
    )
    Box(
        modifier = Modifier
            .padding(bottom = 23.dp)
    ) {
        BottomNavigation(
            modifier = Modifier
                .height(70.dp)
                .padding(8.dp),
            backgroundColor = Color(0xFF1C1B1F)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(ImageVector.vectorResource(id = item.icon), contentDescription = item.title, tint = Color.White) },
                    label = { Text(text = item.title, color = Color.White) },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White
                )
            }
        }
    }
}


sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {
    object Home : BottomNavItem("Home", R.drawable.home_icon, "home")
    object Profile : BottomNavItem("Profile", R.drawable.ic_user, "profile")
    object Search : BottomNavItem("Search", R.drawable.ic_search, "search")
    object Jobs : BottomNavItem("Jobs", R.drawable.job, "jobs")
}