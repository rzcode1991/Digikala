package com.example.digikala.data.model.zarinpal

data class ZarinpalPaymentResponse(
    val data: ZarinpalPaymentData?,
    val errors: List<ZarinpalError>?
)

data class ZarinpalPaymentData(
    val code: Int,
    val message: String,
    val authority: String,
    val fee_type: String,
    val fee: Int
)

data class ZarinpalError(
    val code: Int,
    val message: String,
    val validations: List<Any>
)
