package com.aditys.h_news.repository

import com.aditys.h_news.model.ItemResponse
import com.aditys.h_news.model.NewsResponse
import com.aditys.h_news.model.SearchResponse
import com.aditys.h_news.model.UserResponse
import com.aditys.h_news.network.RetrofitInstance


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
}