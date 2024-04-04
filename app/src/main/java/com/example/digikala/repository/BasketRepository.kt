package com.example.digikala.repository

import com.example.digikala.data.db.CartDao
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.BasketApiInterface
import com.example.digikala.data.network.NetworkResult
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val api: BasketApiInterface,
    private val dao: CartDao
): BaseApiResponse() {

    suspend fun getSuggestedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSuggestedItems()
        }

    suspend fun addCartItem(cart: CartItem){
        dao.addCartItem(cart)
    }

    val allCurrentCartItems = dao.getAllCartItems(CartStatus.CURRENT_CART)
    val allNextCartItems = dao.getAllCartItems(CartStatus.NEXT_CART)

    val totalCountForCurrentCartItems = dao.getTotalCountForCartItems(CartStatus.CURRENT_CART)
    val totalCountForNextCartItems = dao.getTotalCountForCartItems(CartStatus.NEXT_CART)

    suspend fun deleteCartItem(cart: CartItem){
        dao.deleteCartItem(cart)
    }

    suspend fun updateCartItem(cart: CartItem){
        dao.updateCartItem(cart)
    }

    suspend fun changeCartItemStatus(newStatus: CartStatus, id: String){
        dao.changeCartItemStatus(newStatus, id)
    }

}