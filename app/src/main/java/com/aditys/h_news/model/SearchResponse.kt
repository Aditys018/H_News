package com.aditys.h_news.model

data class SearchResponse(
    val hits: List<SearchResult>
)

data class SearchResult(
    val title: String,
    val url: String?,
    val author: String,
    val points: Int,
    val story_text: String?,
    val comment_text: String?,
    val num_comments: Int,
    val objectID: String
)
