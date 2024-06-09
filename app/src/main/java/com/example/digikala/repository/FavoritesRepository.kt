package com.example.digikala.repository

import com.example.digikala.data.db.FavoritesDao
import com.example.digikala.data.model.favorites.FavoriteItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val dao: FavoritesDao
) {

    val allFavoriteItems = dao.getAllFavoriteItems()

    suspend fun addToFavorites(favoriteItem: FavoriteItem){
        dao.addToFavorites(favoriteItem)
    }

    suspend fun deleteFromFavorites(favoriteItem: FavoriteItem){
        dao.deleteFromFavorites(favoriteItem)
    }

    suspend fun clearFavoriteList(){
        dao.clearFavoriteList()
    }

    fun isItemInFavoriteList(itemId: String): Flow<Boolean>{
        return dao.isItemInFavoriteList(itemId)
    }


}