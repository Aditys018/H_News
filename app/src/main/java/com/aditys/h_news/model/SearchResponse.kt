package com.aditys.h_news.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SearchResponse(
    val hits: List<SearchResult>
)

@Keep
data class SearchResult(
    val title: String?,
    @SerializedName("story_title")
    val storyTitle: String?,
    @SerializedName("story_text")
    val storyText: String?,
    val url: String?,
    val author: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("created_at_i")
    val createdAtI: Int?,
    val points: Int?,
    @SerializedName("num_comments")
    val numComments: Int?
)