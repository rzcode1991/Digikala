package com.example.digikala.repository

import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.data.network.ProductDetailsApiInterface
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(
    private val api: ProductDetailsApiInterface
): BaseApiResponse() {

    suspend fun getProductById(id: String): NetworkResult<ProductDetails> =
        safeApiCall {
            api.getProductById(id)
        }

}