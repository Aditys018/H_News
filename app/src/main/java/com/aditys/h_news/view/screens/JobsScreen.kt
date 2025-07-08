package com.aditys.h_news.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.aditys.h_news.model.Job
import com.aditys.h_news.viewmodel.NewsViewModel

@Composable
fun JobsScreen(newsViewModel: NewsViewModel = viewModel()) {
    val jobs by newsViewModel.jobs.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        newsViewModel.fetchJobs()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Job Openings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(jobs) { job ->
                JobItem(job)
            }
        }
    }
}

@Composable
fun JobItem(job: Job) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = job.title ?: "", style = MaterialTheme.typography.titleMedium)
            Text(text = "By: ${job.author ?: ""}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Posted: ${job.created_at ?: ""}", style = MaterialTheme.typography.bodySmall)
            job.url?.let {
                Text(text = it, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}