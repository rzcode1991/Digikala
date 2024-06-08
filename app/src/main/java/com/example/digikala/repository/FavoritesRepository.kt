package com.example.digikala.repository

import com.example.digikala.data.db.FavoritesDao
import com.example.digikala.data.model.favorites.FavoriteItem
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

}