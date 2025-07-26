package com.aditys.h_news.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class JobResponse(
    val hits: List<Job>
)

@Keep
data class Job(
    @SerializedName("created_at")
    val createdAt: String?,
    val title: String?,
    val url: String?,
    val author: String?,
    val objectID: String?
)