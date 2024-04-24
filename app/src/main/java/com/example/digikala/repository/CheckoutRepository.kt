package com.example.digikala.repository

import com.example.digikala.data.model.checkout.OrderRequest
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.CheckoutApiInterface
import com.example.digikala.data.network.NetworkResult
import javax.inject.Inject

class CheckoutRepository @Inject constructor(
    private val api: CheckoutApiInterface
): BaseApiResponse() {

    suspend fun setNewOrder(orderRequest: OrderRequest): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(orderRequest)
        }

}