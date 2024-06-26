package com.example.digikala.data.network

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.checkout.ConfirmPurchaseRequest
import com.example.digikala.data.model.checkout.OrderRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckoutApiInterface {

    @POST("v1/setNewOrder")
    suspend fun setNewOrder(
        @Body orderRequest: OrderRequest
    ): Response<ResponseResult<String>>

    @POST("v1/confirmPurchase")
    suspend fun confirmPurchase(
        @Body confirmPurchase: ConfirmPurchaseRequest
    ): Response<ResponseResult<String>>

}