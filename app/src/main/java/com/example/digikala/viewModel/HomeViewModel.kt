package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.SpecialOfferItem
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val specialOffers = MutableStateFlow<NetworkResult<List<SpecialOfferItem>>>(NetworkResult.Loading())
    val specialSupermarketOffers = MutableStateFlow<NetworkResult<List<SpecialOfferItem>>>(NetworkResult.Loading())

    suspend fun getAllDataFromServer(){
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

        }
    }


}