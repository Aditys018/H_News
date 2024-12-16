package com.aditys.h_news.network

import com.aditys.h_news.model.ItemResponse
import com.aditys.h_news.model.NewsResponse
import com.aditys.h_news.model.SearchResponse
import com.aditys.h_news.model.UserResponse
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.Path


interface HackerNewsApi {
    @GET("items/{id}")
    suspend fun getItem(@Path("id") id: Int): ItemResponse

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): UserResponse

    @GET("search")
    suspend fun search(@Query("query") query: String): SearchResponse

    @GET("search_by_date")
    suspend fun searchByDate(@Query("query") query: String): SearchResponse
}