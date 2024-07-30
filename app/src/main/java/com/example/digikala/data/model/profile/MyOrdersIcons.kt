package com.example.digikala.data.model.profile

import androidx.compose.ui.graphics.painter.Painter
import com.example.digikala.data.model.checkout.Order

data class MyOrdersIcons(
    val name: String,
    val image: Painter,
    val id: String,
    val hasNews: Boolean = false,
    val notifCount: Int = 0,
    val lastInRow: Boolean = false,
    val ordersList: List<Order> = emptyList()
)
