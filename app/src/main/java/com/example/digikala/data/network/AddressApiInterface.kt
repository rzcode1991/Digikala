package com.example.digikala.data.network

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.address.AddAddressRequest
import com.example.digikala.data.model.address.Address
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AddressApiInterface {

    @GET("v1/getUserAddress")
    suspend fun getUserAddressList(
        @Query("token") token: String
    ): Response<ResponseResult<List<Address>>>

    @POST("v1/saveUserAddress")
    suspend fun saveUserAddress(
        @Body address: AddAddressRequest
    ): Response<ResponseResult<String>>

}