package com.example.digikala.data.model.comment

data class NewComment(
    val token: String,
    val productId: String,
    val star: Int,
    val title: String,
    val description: String,
    val userName: String
)
