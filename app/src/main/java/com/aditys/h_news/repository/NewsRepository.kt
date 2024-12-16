package com.aditys.h_news.repository
import com.aditys.h_news.model.ItemResponse
import com.aditys.h_news.model.SearchResponse
import com.aditys.h_news.model.UserResponse
import com.aditys.h_news.network.RetrofitInstance
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class NewsRepository {
    suspend fun getItem(id: Int): ItemResponse {
        return RetrofitInstance.api.getItem(id)
    }

    suspend fun getUser(username: String): UserResponse {
        return RetrofitInstance.api.getUser(username)
    }

    suspend fun search(query: String): SearchResponse {
        return RetrofitInstance.api.search(query)
    }

    suspend fun searchByDate(query: String): SearchResponse {
        return RetrofitInstance.api.searchByDate(query)
    }
    suspend fun getJobs(): List<Job> {
        return RetrofitInstance.api.getJobs()
    }

    suspend fun fetchConcurrentData(id: Int, username: String, query: String): List<Any> = coroutineScope {
        val itemDeferred = async { getItem(id) }
        val userDeferred = async { getUser(username) }
        val searchDeferred = async { search(query) }

        listOf(itemDeferred.await(), userDeferred.await(), searchDeferred.await())
    }
}