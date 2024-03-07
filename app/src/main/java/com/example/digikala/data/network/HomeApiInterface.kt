package com.example.digikala.data.network

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.SpecialOfferItem
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiInterface {

    @GET("v1/getSlider")
    suspend fun getSlider(): Response<ResponseResult<List<Slider>>>

    @GET("v1/getAmazingProducts")
    suspend fun getSpecialOffers(): Response<ResponseResult<List<SpecialOfferItem>>>

}