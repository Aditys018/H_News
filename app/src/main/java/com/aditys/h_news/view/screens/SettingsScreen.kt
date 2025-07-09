package com.aditys.h_news.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1B1F))
            .padding(start = 50.dp, end = 50.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.padding(top = 52.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "About", style = MaterialTheme.typography.titleMedium, color = Color(0xFFF4A261))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2832)),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "App version: 1.0.0", color = Color.White)
                Text(text = "Developed by Aditi Shinde", modifier = Modifier.padding(top = 8.dp), color = Color.White)
            }
        }
    }
}