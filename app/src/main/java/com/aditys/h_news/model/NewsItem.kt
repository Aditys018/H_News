package com.aditys.h_news.model

data class NewsItem(
    val created_at: String,
    val title: String,
    val url: String,
    val author: String,
    val points: Int
)

data class NewsResponse(
    val hits: List<NewsItem>
)