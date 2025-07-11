package com.aditys.h_news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditys.h_news.model.ItemResponse
import com.aditys.h_news.model.Job
import com.aditys.h_news.model.SearchResponse
import com.aditys.h_news.model.UserResponse
import com.aditys.h_news.repository.INewsRepository
import com.aditys.h_news.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository: INewsRepository = NewsRepository()

    val item: MutableLiveData<ItemResponse> = MutableLiveData()
    val user: MutableLiveData<UserResponse> = MutableLiveData()
    val searchResults: MutableLiveData<SearchResponse> = MutableLiveData()
    val jobs: MutableLiveData<List<Job>> = MutableLiveData()

    fun fetchItem(id: Int) {
        viewModelScope.launch {
            val response = repository.getItem(id)
            item.postValue(response)
        }
    }

    fun fetchUser(username: String) {
        viewModelScope.launch {
            val response = repository.getUser(username)
            user.postValue(response)
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            val response = repository.search(query)
            searchResults.postValue(response)
        }
    }

    fun searchByDate(query: String) {
        viewModelScope.launch {
            val response = repository.searchByDate(query)
            searchResults.postValue(response)
        }
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.search(query)
                searchResults.postValue(response)
            } catch (e: Exception) {
            }
        }
    }

    fun fetchJobs() {
        viewModelScope.launch {
            try {
                val jobList = repository.getJobs()
                jobs.postValue(jobList)
            } catch (e: Exception) {

            }
        }
    }

    init {
        search("jobs")
    }
}