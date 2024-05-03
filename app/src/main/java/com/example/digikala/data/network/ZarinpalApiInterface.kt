package com.example.digikala.data.network

import com.example.digikala.data.model.zarinpal.PaymentRequest
import com.example.digikala.data.model.zarinpal.VerifyRequest
import com.example.digikala.data.model.zarinpal.ZarinVerifyResponse
import com.example.digikala.data.model.zarinpal.ZarinpalPaymentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ZarinpalApiInterface {

    @POST("v4/payment/request.json")
    suspend fun paymentRequest(
        @Body paymentRequest: PaymentRequest
    ): Response<ZarinpalPaymentResponse>

    @POST("v4/payment/verify.json")
    suspend fun verifyPayment(
        @Body verifyRequest: VerifyRequest
    ): Response<ZarinVerifyResponse>

}