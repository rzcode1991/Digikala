package com.example.digikala.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.checkout.DayAndDate
import com.example.digikala.data.model.checkout.OrderStatus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromCartItemList(value: List<CartItem>): ByteArray {
        val gson = Gson()
        val type = object : TypeToken<List<CartItem>>() {}.type
        return gson.toJson(value, type).toByteArray()
    }

    @TypeConverter
    fun toCartItemList(value: ByteArray): List<CartItem> {
        val gson = Gson()
        val type = object : TypeToken<List<CartItem>>() {}.type
        return gson.fromJson(String(value), type)
    }

    @TypeConverter
    fun fromOrderStatus(orderStatus: OrderStatus): String {
        return orderStatus.name
    }

    @TypeConverter
    fun toOrderStatus(orderStatus: String): OrderStatus {
        return OrderStatus.valueOf(orderStatus)
    }

}