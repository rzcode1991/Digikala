package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartPriceDetail
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.repository.BasketRepository
import com.example.digikala.ui.screens.basket.BasketScreenState
import com.example.digikala.utils.DigitHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    val nextCartCounter = MutableStateFlow(0)

    val currentCartPriceDetail = MutableStateFlow(CartPriceDetail(0, 0, 0, 0, 0))

    val totalCountForCurrentCartItems = repository.totalCountForCurrentCartItems
    val totalCountForNextCartItems = repository.totalCountForNextCartItems

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
            launch {
                repository.allNextCartItems.collectLatest { items ->
                    nextCartItemsCounter(items)
                }
            }
            launch {
                repository.allCurrentCartItems.collectLatest { items ->
                    calculateCartPriceDetail(items)
                }
            }
        }
    }

    private suspend fun nextCartItemsCounter(items: List<CartItem>){
        var totalCount = 0
        items.forEach { item ->
            totalCount += item.count
        }
        nextCartCounter.emit(totalCount)
    }

    private suspend fun calculateCartPriceDetail(items: List<CartItem>){
        var totalCount = 0
        var totalPrice = 0
        var totalDiscountPercent = 0
        var totalDiscountPrice = 0

        items.forEach { item ->
            totalCount += item.count
            totalPrice += item.count * item.price
            totalDiscountPercent += (item.discountPercent) / items.size
            val itemDiscountPrice = item.count * (item.price - DigitHelper.applyDiscount(
                price = item.price.toLong(),
                discountPercent = item.discountPercent
            ))
            totalDiscountPrice += itemDiscountPrice.toInt()
        }
        val totalFinalPrice = totalPrice - totalDiscountPrice
        currentCartPriceDetail.emit(
            CartPriceDetail(
                totalCount = totalCount,
                totalPrice = totalPrice.toLong(),
                totalDiscountPercent = totalDiscountPercent.toLong(),
                totalDiscountPrice = totalDiscountPrice.toLong(),
                totalFinalPrice = totalFinalPrice.toLong()
            )
        )
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

    fun deleteAllCurrentCartItems(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCurrentCartItems()
        }
    }

    fun clearAllCartItems(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearAllCartItems()
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