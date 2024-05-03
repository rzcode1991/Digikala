package com.example.digikala.data.model.zarinpal

data class PaymentRequest(
    val merchant_id: String,
    val amount: Int,
    val callback_url: String,
    val description: String,
    val metadata: Map<String, String>
)