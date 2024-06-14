package com.example.digikala.viewModel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.favorites.FavoriteItem
import com.example.digikala.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
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
    val isFavoriteItem = MutableStateFlow(false)

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

    fun isItemInFavoriteList(itemId: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.isItemInFavoriteList(itemId).collectLatest {
                isFavoriteItem.emit(it)
            }
        }
    }

    fun clearFavoriteList(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearFavoriteList()
        }
    }

    var myFavoriteItem by mutableStateOf<FavoriteItem?>(null)

    @OptIn(ExperimentalMaterialApi::class)
    fun bottomSheetHandler(
        favoriteItem: FavoriteItem,
        scope: CoroutineScope,
        bottomSheetState: ModalBottomSheetState
    ){

        myFavoriteItem = favoriteItem

        scope.launch {
            if (bottomSheetState.isVisible){
                bottomSheetState.hide()
            }else{
                bottomSheetState.show()
            }
        }

    }

}