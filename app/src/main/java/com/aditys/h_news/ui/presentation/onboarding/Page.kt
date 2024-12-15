package com.aditys.h_news.ui.presentation.onboarding

import androidx.annotation.DrawableRes
import com.aditys.h_news.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image : Int
)

val pages = listOf(
    Page(
        title = "Lorem ipsum",
        description = "Lorem ipsum dolor sit",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem ipsum",
        description = "Lorem ipsum dolor sit",
        image = R.drawable.onboardingtwo
    ),
    Page(
        title = "Lorem ipsum",
        description = "Lorem ipsum dolor sit",
        image = R.drawable.onboarding3
    ),
)
