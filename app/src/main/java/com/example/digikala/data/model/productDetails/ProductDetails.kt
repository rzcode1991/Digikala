package com.example.digikala.data.model.productDetails

data class ProductDetails(
    val _id: String,
    val agreeCount: Int,
    val agreePercent: Int,
    val colors: List<Color>,
    val commentCount: Int,
    val comments: List<Comment>,
    val discountPercent: Int,
    val imageSlider: List<ImageSlider>,
    val name: String,
    val price: Int,
    val questionCount: Int,
    val seller: String,
    val star: Double,
    val starCount: Int
)