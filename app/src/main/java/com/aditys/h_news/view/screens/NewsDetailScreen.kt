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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.AnnotatedString
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

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
        if (!content.isNullOrBlank() && content != "No content available") {
            Text(content, color = Color.White)
        } else if (!url.isNullOrBlank()) {
            val context = LocalContext.current
            ClickableText(
                text = AnnotatedString("Read more at: $url"),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF2196F3)),
                onClick = {
                    if (url.startsWith("https://")) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    } else {
                        android.widget.Toast.makeText(context, "Warning: Not a secure (HTTPS) link. Link will not be opened.", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            )
        } else {
            Text("No content available", color = Color.White)
        }
    }
}