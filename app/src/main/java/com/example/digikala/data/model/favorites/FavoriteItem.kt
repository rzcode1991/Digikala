package com.example.digikala.data.model.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikala.utils.Constants.FAVORITE_ITEM_TABLE

@Entity(tableName = FAVORITE_ITEM_TABLE)
data class FavoriteItem(
    @PrimaryKey
    val itemId: String,
    val discountPercent: Int,
    val image: String,
    val name: String,
    val price: Int,
    val seller: String,
    val star: Double
)
