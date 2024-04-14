package com.example.digikala.data.model.profile

import androidx.compose.ui.graphics.painter.Painter

data class MyOrdersIcons(
    val name: String,
    val image: Painter,
    val hasNews: Boolean = false,
    val notifCount: Int = 0,
    val lastInRow: Boolean = false
)
