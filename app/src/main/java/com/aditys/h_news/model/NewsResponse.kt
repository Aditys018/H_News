package com.aditys.h_news.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsItem(
    @SerializedName("created_at")
    val createdAt: String,
    val title: String,
    val url: String,
    val author: String,
    val points: Int
)

@Keep
data class NewsResponse(
    val hits: List<NewsItem>
)