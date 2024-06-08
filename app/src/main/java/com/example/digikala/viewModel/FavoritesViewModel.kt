package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.favorites.FavoriteItem
import com.example.digikala.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
): ViewModel() {


    val allFavoriteItems = MutableStateFlow<List<FavoriteItem>>(emptyList())

    fun getAllFavorites(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.allFavoriteItems.collectLatest { favoriteItemList ->
                allFavoriteItems.emit(favoriteItemList)
            }
        }
    }
    fun addToFavorites(favoriteItem: FavoriteItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToFavorites(favoriteItem)
        }
    }

    fun deleteFromFavorites(favoriteItem: FavoriteItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFromFavorites(favoriteItem)
        }
    }

}