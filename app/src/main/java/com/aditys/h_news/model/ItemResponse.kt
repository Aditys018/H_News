package com.aditys.h_news.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ItemResponse(
    val id: Int,
    @SerializedName("created_at")
    val createdAt: String,
    val author: String,
    val title: String?,
    val url: String?,
    val text: String?,
    val points: Int,
    @SerializedName("parent_id")
    val parentId: Int?,
    val children: List<ItemResponse>
)