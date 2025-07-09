package com.aditys.h_news.view.screens

import com.aditys.h_news.R

sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {
    object Home : BottomNavItem("Home", R.drawable.house, "home")
    object Search : BottomNavItem("Search", R.drawable.ic_search, "search")
    object Jobs : BottomNavItem("Jobs", R.drawable.job, "jobs")
    object Settings : BottomNavItem("Settings", R.drawable.ic_settings, "settings")
}