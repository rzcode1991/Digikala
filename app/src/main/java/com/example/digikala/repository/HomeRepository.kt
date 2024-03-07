package com.example.digikala.repository

import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.SpecialOfferItem
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.HomeApiInterface
import com.example.digikala.data.network.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: HomeApiInterface
): BaseApiResponse() {

    suspend fun getSlider(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }

    suspend fun getSpecialOffers(): NetworkResult<List<SpecialOfferItem>> =
        safeApiCall {
            api.getSpecialOffers()
        }

}