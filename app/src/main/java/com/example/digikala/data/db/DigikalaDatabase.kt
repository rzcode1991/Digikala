package com.example.digikala.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.favorites.FavoriteItem
import com.example.digikala.data.model.zarinpal.RefId

@Database(
    entities = [CartItem::class, RefId::class, FavoriteItem::class],
    version = 3,
    exportSchema = false
)
abstract class DigikalaDatabase: RoomDatabase() {

    abstract fun cartDao(): CartDao
    abstract fun refIdDao(): RefIdDao
    abstract fun favoritesDao(): FavoritesDao

}