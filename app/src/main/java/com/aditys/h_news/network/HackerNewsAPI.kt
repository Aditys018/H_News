package com.aditys.h_news.network

import com.aditys.h_news.model.ItemResponse
import com.aditys.h_news.model.JobResponse
import com.aditys.h_news.model.SearchResponse
import com.aditys.h_news.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HackerNewsApi {
    @GET("item/{id}")
    suspend fun getItem(@Path("id") id: Int): ItemResponse

    @GET("user/{username}")
    suspend fun getUser(@Path("username") username: String): UserResponse

    @GET("search")
    suspend fun search(@Query("query") query: String): SearchResponse

    @GET("search_by_date")
    suspend fun searchByDate(@Query("query") query: String): SearchResponse

    @GET("search_by_date")
    suspend fun getJobs(@Query("tags") tags: String = "job"): JobResponse

    @GET("all_items")
    suspend fun getAllItems(): List<ItemResponse>
}