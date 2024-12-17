package com.aditys.h_news.model

data class ItemResponse(
    val id: Int,
    val created_at: String,
    val author: String,
    val title: String?,
    val url: String?,
    val text: String?,
    val points: Int,
    val parent_id: Int?,
    val children: List<ItemResponse>
)
