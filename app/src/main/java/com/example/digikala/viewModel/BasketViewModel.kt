package com.example.digikala.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.repository.BasketRepository
import com.example.digikala.ui.screens.basket.BasketScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: BasketRepository
): ViewModel() {

    val allProducts = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())

    private val _allCurrentCartItems = MutableStateFlow<BasketScreenState<List<CartItem>>>(BasketScreenState.Loading)
    val allCurrentCartItems: StateFlow<BasketScreenState<List<CartItem>>> = _allCurrentCartItems

    private val _allNextCartItems = MutableStateFlow<BasketScreenState<List<CartItem>>>(BasketScreenState.Loading)
    val allNextCartItems : StateFlow<BasketScreenState<List<CartItem>>> = _allNextCartItems

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                repository.allCurrentCartItems.collectLatest {
                    _allCurrentCartItems.emit(BasketScreenState.Success(it))
                }
            }
            launch {
                repository.allNextCartItems.collectLatest {
                    _allNextCartItems.emit(BasketScreenState.Success(it))
                }
            }
        }
    }

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

    fun deleteCartItem(cart: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCartItem(cart)
        }
    }

    fun updateCartItem(cart: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCartItem(cart)
        }
    }

    fun changeCartItemStatus(newStatus: CartStatus, id: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCartItemStatus(newStatus, id)
        }
    }


}