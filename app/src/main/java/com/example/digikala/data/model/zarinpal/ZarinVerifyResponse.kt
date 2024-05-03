package com.example.digikala.data.model.zarinpal

data class ZarinVerifyResponse(
    val data: VerifyData,
    val errors: List<String>
)

data class VerifyData(
    val code: Int,
    val message: String,
    val cardHash: String,
    val cardPan: String,
    val refId: Int,
    val feeType: String,
    val fee: Int
)
