package com.aditys.h_news.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aditys.h_news.viewmodel.NewsViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import com.aditys.h_news.model.SearchResult
import com.aditys.h_news.model.Job
import com.google.gson.Gson
import android.net.Uri


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    newsViewModel: NewsViewModel = viewModel(),
    onPostClick: (SearchResult) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    val searchResults by newsViewModel.searchResults.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1B1F))
            .padding(16.dp)
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                newsViewModel.searchNews(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(56.dp),
            placeholder = { Text("Search news...", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(28.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF2D2832),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            singleLine = true
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            searchResults?.hits?.let { items ->
                items(items) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onPostClick(item) },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2832)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = item.title ?: "No Title",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.author ?: "Unknown",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFF4A261)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    Surface {
        SearchScreen()
    }
}