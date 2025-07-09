package com.aditys.h_news.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.asPaddingValues

@Composable
fun NewsDetailScreen(
    title: String,
    author: String,
    content: String,
    url: String? = null,
    onBack: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White // or Color(0xFFF4A261) for orange
                    )
                }
                Spacer(Modifier.width(8.dp))
            }
            Text(
                title,
                color = Color(0xFFF4A261),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Spacer(Modifier.height(8.dp))
        Text("by $author", color = Color.Gray)
        Spacer(Modifier.height(8.dp))
        if (!content.isNullOrBlank() && content != url) {
            Text(content, color = Color.White)
        } else if (!url.isNullOrBlank()) {
            Text(url, color = Color(0xFF2196F3))
        } else {
            Text("No content available", color = Color.White)
        }
    }
}