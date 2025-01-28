package com.aditys.h_news.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aditys.h_news.theme.H_NewsTheme
import com.aditys.h_news.view.pages.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()
        setContent {
            H_NewsTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { innerPadding ->
                    Box(modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") { HomePage() }
                            composable("profile") { ProfilePage() }
                            composable("search") { SearchPage() }
                            composable("bookmarks") { BookmarksPage() }
                        }
                    }
                }
            }
        }
    }
}