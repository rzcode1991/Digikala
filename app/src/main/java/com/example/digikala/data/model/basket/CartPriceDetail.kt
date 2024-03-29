package com.example.digikala.data.model.basket

data class CartPriceDetail(
    val totalCount: Int,
    val totalPrice: Long,
    val totalDiscountPercent: Long,
    val totalDiscountPrice: Long,
    val totalFinalPrice: Long
)
