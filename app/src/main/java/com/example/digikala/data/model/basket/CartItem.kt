package com.example.digikala.data.model.basket

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikala.utils.Constants.SHOPPING_CART_TABLE

@Entity(tableName = SHOPPING_CART_TABLE)
data class CartItem(
    @PrimaryKey
    val itemId: String,
    val discountPercent: Int,
    val image: String,
    val name: String,
    val price: Int,
    val seller: String,
    val count: Int,
    val cartStatus: CartStatus
)
