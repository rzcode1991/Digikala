package com.example.digikala.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: BasketRepository
): ViewModel() {

    val allProducts = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val allCurrentCartItems: Flow<List<CartItem>> = repository.allCurrentCartItems
    suspend fun getSuggestedItems(){
        viewModelScope.launch {
            allProducts.emit(repository.getSuggestedItems())
        }
    }

    fun addCartItem(cart: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCartItem(cart)
        }
    }


}