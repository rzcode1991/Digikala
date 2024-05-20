package com.example.digikala.data.network

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.productDetails.ProductDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDetailsApiInterface {

    @GET("v1/getProductById")
    suspend fun getProductById(
        @Query("id") id: String
    ): Response<ResponseResult<ProductDetails>>

}