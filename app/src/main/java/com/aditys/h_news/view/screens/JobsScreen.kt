package com.aditys.h_news.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.AnnotatedString
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aditys.h_news.model.Job
import com.aditys.h_news.viewmodel.NewsViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext

@Composable
fun JobsScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    onJobClick: (Job) -> Unit = {}
) {
    val jobs by newsViewModel.jobs.observeAsState(emptyList())
    val (searchQuery, setSearchQuery) = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        newsViewModel.fetchJobs()
    }

    val filteredJobs = jobs.filter { job ->
        val query = searchQuery.trim().lowercase()
        query.isEmpty() ||
                (job.title?.lowercase()?.contains(query) == true) ||
                (job.author?.lowercase()?.contains(query) == true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1B1F))
            .padding(16.dp)
    ) {
        Text(
            text = "Job Openings",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = searchQuery,
            onValueChange = setSearchQuery,
            label = { Text("Search jobs...", color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFF4A261),
                unfocusedBorderColor = Color(0xFFF4A261),
                cursorColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        )
        LazyColumn {
            items(filteredJobs) { job ->
                JobItem(job, onClick = { onJobClick(job) })
            }
        }
    }
}

@Composable
fun JobItem(job: Job, onClick: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2832)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = job.title ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text = "By: ${job.author ?: ""}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFF4A261)
            )
            Text(
                text = "Posted: ${job.createdAt ?: ""}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            job.url?.let {
                ClickableText(
                    text = AnnotatedString(it),
                    style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFF4A261)),
                    onClick = { _ ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}