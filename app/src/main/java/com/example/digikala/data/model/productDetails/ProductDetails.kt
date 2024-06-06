package com.example.digikala.data.model.productDetails

import com.google.gson.JsonObject

data class ProductDetails(
    val _id: String,
    val agreeCount: Int,
    val agreePercent: Int,
    val category: String,
    val categoryId: String,
    val colors: List<Color>,
    val commentCount: Int,
    val comments: List<Comment>,
    val description: String? = null,
    val discountPercent: Int,
    val imageSlider: List<ImageSlider>,
    val name: String,
    val price: Int,
    val priceList: List<Price>? = null,
    val questionCount: Int,
    val seller: String,
    val star: Double,
    val starCount: Int,
    val technicalFeatures: JsonObject? = null,
    val viewCount: Int? = null
)