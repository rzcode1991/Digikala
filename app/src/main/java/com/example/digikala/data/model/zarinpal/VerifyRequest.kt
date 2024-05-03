package com.example.digikala.data.model.zarinpal

data class VerifyRequest(
    val merchant_id: String,
    val amount: Int,
    val authority: String
)
