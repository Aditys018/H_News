package com.aditys.h_news.model



data class Job(
    val id: String,
    val created_at: String,
    val title: String,
    val url: String?,
    val author: String,
    val points: Int,
    val story_text: String?,
    val comment_text: String?,
    val num_comments: Int,
    val story_id: Int?,
    val story_title: String?,
    val story_url: String?,
    val parent_id: Int?,
    val created_at_i: Int,
    val _tags: List<String>,
    val objectID: String
)