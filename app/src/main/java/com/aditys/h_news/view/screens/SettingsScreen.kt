package com.aditys.h_news.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.net.Uri

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1B1F))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "ABOUT",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(32.dp))
        // Links
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Links",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            ClickableText(
                text = AnnotatedString("Send Feedback to developer"),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFFF4A261), fontSize = MaterialTheme.typography.titleMedium.fontSize),
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:adityssh.in@gmail.com")
                    }
                    context.startActivity(intent)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ClickableText(
                text = AnnotatedString("View Source code at GitHub"),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFFF4A261), fontSize = MaterialTheme.typography.titleMedium.fontSize),
                onClick = {
                    val url = "https://github.com/Aditys018/H_News"
                    if (url.startsWith("https://")) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    } else {
                        android.widget.Toast.makeText(context, "Warning: Not a secure (HTTPS) link. Link will not be opened.", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ClickableText(
                text = AnnotatedString("View Privacy policy"),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFFF4A261), fontSize = MaterialTheme.typography.titleMedium.fontSize),
                onClick = {
                    val url = "https://github.com/Aditys018/H_News#privacy-policy"
                    if (url.startsWith("https://")) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    } else {
                        android.widget.Toast.makeText(context, "Warning: Not a secure (HTTPS) link. Link will not be opened.", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "connect with the developer",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_share),
                contentDescription = "LinkedIn",
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        val url = "https://www.linkedin.com/in/aditirshinde/"
                        if (url.startsWith("https://")) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        } else {
                            android.widget.Toast.makeText(context, "Warning: Not a secure (HTTPS) link. Link will not be opened.", android.widget.Toast.LENGTH_SHORT).show()
                        }
                    }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = android.R.drawable.ic_dialog_email),
                contentDescription = "Email",
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:adityssh.in@gmail.com")
                        }
                        context.startActivity(intent)
                    }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_info_details),
                contentDescription = "GitHub",
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        val url = "https://github.com/Aditys018"
                        if (url.startsWith("https://")) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        } else {
                            android.widget.Toast.makeText(context, "Warning: Not a secure (HTTPS) link. Link will not be opened.", android.widget.Toast.LENGTH_SHORT).show()
                        }
                    }
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        // Privacy Policy (inline for reference, not shown in UI)
        /*
        Text(
            text = "Privacy Policy: This app does not collect, store, or share any personal user data. All news content is fetched from public APIs.",
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 24.dp)
        )
        */
    }
}