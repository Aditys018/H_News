package com.aditys.h_news.repository

import com.aditys.h_news.model.ItemResponse
import com.aditys.h_news.model.SearchResponse
import com.aditys.h_news.model.UserResponse
import kotlinx.coroutines.Job

interface INewsRepository {
    suspend fun getItem(id: Int): ItemResponse
    suspend fun getUser(username: String): UserResponse
    suspend fun search(query: String): SearchResponse
    suspend fun searchByDate(query: String): SearchResponse
    suspend fun getJobs(): List<Job>
    suspend fun fetchConcurrentData(id: Int, username: String, query: String): List<Any>
}