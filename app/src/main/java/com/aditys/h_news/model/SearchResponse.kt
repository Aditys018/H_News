package com.aditys.h_news.model

data class SearchResponse(
    val hits: List<SearchResult>
)

data class SearchResult(
    val title: String?,
    val story_title: String?,
    val story_text: String?,
    val url: String?,
    val author: String?,
    val created_at: String?,
    val created_at_i: Int?,
    val points: Int?,
    val num_comments: Int?,
    val objectID: String
)
