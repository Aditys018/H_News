package com.aditys.h_news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditys.h_news.model.Job
import com.aditys.h_news.model.SearchResult
import com.aditys.h_news.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

enum class NewsFilter { TRENDING, NEW, PAST, SHOW, JOBS }

data class HomeUiState(
    val trending: SearchResult? = null,
    val newsList: List<SearchResult> = emptyList(),
    val jobsList: List<Job> = emptyList(),
    val selectedFilter: NewsFilter = NewsFilter.TRENDING,
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel(
    private val repository: NewsRepository = NewsRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchTrending()
    }

    fun onFilterSelected(filter: NewsFilter) {
        _uiState.value = _uiState.value.copy(selectedFilter = filter, isLoading = true, error = null)
        when (filter) {
            NewsFilter.TRENDING -> fetchTrending()
            NewsFilter.NEW -> fetchNew()
            NewsFilter.PAST -> fetchPast()
            NewsFilter.SHOW -> fetchShow()
            NewsFilter.JOBS -> fetchJobs()
        }
    }

    private fun fetchTrending() {
        viewModelScope.launch {
            try {
                val trendingList = repository.search("points")
                val trending = trendingList.hits.firstOrNull()
                _uiState.value = _uiState.value.copy(
                    trending = trending,
                    newsList = trendingList.hits,
                    jobsList = emptyList(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun fetchNew() {
        viewModelScope.launch {
            try {
                val newList = repository.searchByDate("story")
                _uiState.value = _uiState.value.copy(
                    newsList = newList.hits,
                    jobsList = emptyList(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun fetchPast() {
        viewModelScope.launch {
            try {
                val pastList = repository.search("story")
                _uiState.value = _uiState.value.copy(
                    newsList = pastList.hits,
                    jobsList = emptyList(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun fetchShow() {
        viewModelScope.launch {
            try {
                val showList = repository.search("show")
                _uiState.value = _uiState.value.copy(
                    newsList = showList.hits,
                    jobsList = emptyList(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun fetchJobs() {
        viewModelScope.launch {
            try {
                val jobs = repository.getJobs()
                _uiState.value = _uiState.value.copy(
                    newsList = emptyList(),
                    jobsList = jobs,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}

fun epochToDateString(epoch: Int): String {
    val date = Date(epoch * 1000L)
    val format = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    return format.format(date)
}