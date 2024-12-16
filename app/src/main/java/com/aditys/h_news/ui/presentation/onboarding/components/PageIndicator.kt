package com.aditys.h_news.ui.presentation.onboarding.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.aditys.h_news.ui.presentation.onboarding.dimentions.IndicatorSize

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    PageSize: Int,
    selectedPage:Int,
    selectedColor: Color = Color.White,
    unselectedColor: Color = Color.Gray
){
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween){
        repeat(PageSize){ page ->
            Box(modifier = Modifier.size(IndicatorSize).clip(CircleShape)
                .background(color=if(page == selectedPage) selectedColor else unselectedColor))

        }
    }
}