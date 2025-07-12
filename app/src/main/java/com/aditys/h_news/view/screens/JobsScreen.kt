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

@Composable
fun JobsScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    onJobClick: (Job) -> Unit = {}
) {
    val jobs by newsViewModel.jobs.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        newsViewModel.fetchJobs()
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
        LazyColumn {
            items(jobs) { job ->
                JobItem(job, onClick = { onJobClick(job) })
            }
        }
    }
}

@Composable
fun JobItem(job: Job, onClick: () -> Unit) {
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
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF2196F3)
                )
            }
        }
    }
}