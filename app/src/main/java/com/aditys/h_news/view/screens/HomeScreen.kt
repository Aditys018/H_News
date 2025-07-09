package com.aditys.h_news.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aditys.h_news.model.SearchResult
import com.aditys.h_news.model.Job
import com.aditys.h_news.viewmodel.HomeViewModel
import com.aditys.h_news.viewmodel.NewsFilter

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF39343B))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Trending Today", color = Color.White, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        uiState.trending?.let {
            TrendingCard(it)
        }
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { viewModel.onFilterSelected(NewsFilter.TRENDING) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4A261))
        ) {
            Text("Explore top trending", color = Color.Black, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(16.dp))
        Text("see what's happening", color = Color.White, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        FilterButtons(
            selected = uiState.selectedFilter,
            onSelected = { viewModel.onFilterSelected(it) }
        )
        Spacer(Modifier.height(16.dp))
        if (uiState.isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else if (uiState.error != null) {
            Text("Error: ${uiState.error}", color = Color.Red)
        } else {
            when (uiState.selectedFilter) {
                NewsFilter.JOBS -> JobsList(uiState.jobsList)
                else -> NewsList(uiState.newsList)
            }
        }
    }
}

@Composable
fun TrendingCard(item: SearchResult) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2D2832), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = item.title ?: "",
            color = Color(0xFFF4A261),
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = item.story_text ?: item.url ?: "",
            color = Color.White
        )
        Spacer(Modifier.height(8.dp))
        Row {
            Text("${item.points ?: 0} points", color = Color(0xFFF4A261))
            Spacer(Modifier.width(16.dp))
            Text("${item.num_comments ?: 0} comments", color = Color(0xFFF4A261))
        }
    }
}

@Composable
fun FilterButtons(selected: NewsFilter, onSelected: (NewsFilter) -> Unit) {
    val filters = listOf(
        NewsFilter.NEW to "new",
        NewsFilter.PAST to "past",
        NewsFilter.SHOW to "show",
        NewsFilter.JOBS to "jobs"
    )
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        filters.forEach { (filter, label) ->
            val isSelected = selected == filter
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .background(
                        if (isSelected) Color(0xFFF4A261) else Color(0xFF2D2832),
                        RoundedCornerShape(8.dp)
                    )
                    .clickable { onSelected(filter) }
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text(
                    label,
                    color = if (isSelected) Color.Black else Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun NewsList(news: List<SearchResult>) {
    Column {
        news.forEach { item ->
            NewsCard(item)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun NewsCard(item: SearchResult) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2D2832), RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Text(item.title ?: "", color = Color.White, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text("by ${item.author ?: "unknown"}", color = Color(0xFFF4A261))
        Spacer(Modifier.height(4.dp))
        Row {
            Text("${item.points ?: 0} points", color = Color(0xFFF4A261))
            Spacer(Modifier.width(16.dp))
            Text("${item.num_comments ?: 0} comments", color = Color(0xFFF4A261))
        }
    }
}

@Composable
fun JobsList(jobs: List<Job>) {
    Column {
        jobs.forEach { job ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2D2832), RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Text(job.title ?: "", color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))
                Text(job.author ?: "", color = Color(0xFFF4A261))
                Spacer(Modifier.height(4.dp))
                Text(job.url ?: "", color = Color(0xFFF4A261))
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}