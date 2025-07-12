package com.aditys.h_news.model

import androidx.annotation.Keep

@Keep
data class UserResponse(
    val username: String,
    val about: String,
    val karma: Int
)