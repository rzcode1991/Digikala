package com.example.digikala.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.favorites.FavoriteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorites(favoriteItem: FavoriteItem)

    @Query("select * from favorite_item")
    fun getAllFavoriteItems(): Flow<List<FavoriteItem>>

    @Delete
    suspend fun deleteFromFavorites(favoriteItem: FavoriteItem)

}