package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val specialOffers = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val specialSupermarketOffers = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val proposalCards = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val mainCategories = MutableStateFlow<NetworkResult<List<MainCategory>>>(NetworkResult.Loading())
    val centerBanners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val bestSellerProducts = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val mostVisitedProducts = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val mostFavoriteProducts = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val mostDiscountedProducts = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())

    suspend fun getAllDataFromServer() {
        viewModelScope.launch {

            // fire and forget
            launch {
                slider.emit(repository.getSlider())
            }

            launch {
                specialOffers.emit(repository.getSpecialOffers())
            }

            launch {
                specialSupermarketOffers.emit(repository.getSpecialSupermarketOffers())
            }

            launch {
                proposalCards.emit(repository.getProposalCards())
            }

            launch {
                mainCategories.emit(repository.getCategories())
            }

            launch {
                centerBanners.emit(repository.getCenterBanners())
            }

            launch {
                bestSellerProducts.emit(repository.getBestsellerProducts())
            }

            launch {
                mostVisitedProducts.emit(repository.getMostVisitedProducts())
            }

            launch {
                mostFavoriteProducts.emit(repository.getMostFavoriteProducts())
            }

            launch {
                mostDiscountedProducts.emit(repository.getMostDiscountedProducts())
            }

        }
    }


}