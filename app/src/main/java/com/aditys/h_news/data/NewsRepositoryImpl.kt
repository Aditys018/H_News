package com.aditys.h_news.data

import com.aditys.h_news.model.ItemResponse
import com.aditys.h_news.model.Job
import com.aditys.h_news.model.SearchResponse
import com.aditys.h_news.model.UserResponse
import com.aditys.h_news.network.HackerNewsApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl
@Inject
constructor(
    private val apiService: HackerNewsApi
) : NewsRepository {
    override suspend fun getItem(id: Int): ItemResponse {
        return apiService.getItem(id)
    }

    override suspend fun getUser(username: String): UserResponse {
        return apiService.getUser(username)
    }

    override suspend fun search(query: String): SearchResponse {
        return apiService.search(query)
    }

    override suspend fun searchByDate(query: String): SearchResponse {
        return apiService.searchByDate(query)
    }

    override suspend fun getJobs(): List<Job> {
        return apiService.getJobs().hits
    }

    override suspend fun fetchConcurrentData(id: Int, username: String, query: String): List<Any> =
        coroutineScope {
            val itemDeferred = async { getItem(id) }
            val userDeferred = async { getUser(username) }
            val searchDeferred = async { search(query) }

            listOf(itemDeferred.await(), userDeferred.await(), searchDeferred.await())
        }

    override suspend fun getAllItems(): List<ItemResponse> {
        return apiService.getAllItems()
    }
}