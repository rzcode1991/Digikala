package com.example.digikala.repository

import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.SpecialOfferItem
import com.example.digikala.data.model.home.StoreProduct
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

    suspend fun getSpecialSupermarketOffers(): NetworkResult<List<SpecialOfferItem>> =
        safeApiCall {
            api.getSpecialSupermarketOffers()
        }

    suspend fun getProposalCards(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getProposalCards()
        }

    suspend fun getCategories(): NetworkResult<List<MainCategory>> =
        safeApiCall {
            api.getCategories()
        }

    suspend fun getCenterBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getCenterBanners()
        }

    suspend fun getBestsellerProducts(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getBestsellerProducts()
        }

    suspend fun getMostVisitedProducts(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getMostVisitedProducts()
        }

    suspend fun getMostFavoriteProducts(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getMostFavoriteProducts()
        }

    suspend fun getMostDiscountedProducts(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getMostDiscountedProducts()
        }

}