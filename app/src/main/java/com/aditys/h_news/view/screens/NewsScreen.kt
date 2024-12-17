package com.aditys.h_news.view.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aditys.h_news.viewmodel.NewsViewModel


@Composable
fun NewsScreen(newsViewModel: NewsViewModel = viewModel()) {
    val searchResults by newsViewModel.searchResults.observeAsState()

    LazyColumn {
        searchResults?.hits?.let { items ->
            items(items) { item ->
                Text(text = item.title, style = MaterialTheme.typography.titleLarge)
                Text(text = item.author, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface {
        NewsScreen()
    }
}