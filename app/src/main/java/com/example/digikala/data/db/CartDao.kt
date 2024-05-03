package com.example.digikala.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCartItem(cart: CartItem)

    @Query("select * from shopping_cart where cartStatus=:status")
    fun getAllCartItems(status: CartStatus): Flow<List<CartItem>>

    @Delete
    suspend fun deleteCartItem(cart: CartItem)

    @Query("delete from shopping_cart where cartStatus=:status")
    suspend fun deleteAllItems(status: CartStatus)

    @Update
    suspend fun updateCartItem(cart: CartItem)

    @Query("update shopping_cart set cartStatus=:newStatus where itemId=:id")
    suspend fun changeCartItemStatus(newStatus: CartStatus, id: String)

    @Query("select total(count) as count from shopping_cart where cartStatus=:status")
    fun getTotalCountForCartItems(status: CartStatus): Flow<Int>

}