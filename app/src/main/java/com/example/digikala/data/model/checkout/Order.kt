package com.example.digikala.data.model.checkout

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.utils.Constants.ORDER_TABLE

@Entity(tableName = ORDER_TABLE)
data class Order(
    @PrimaryKey
    val orderId: String,
    val items: List<CartItem>,
    val totalPrice: Long,
    val orderDate: String,
    val orderStatus: OrderStatus
)
