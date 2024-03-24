package com.example.digikala.ui.screens.basket

sealed class BasketScreenState<out T> {

    data object Loading: BasketScreenState<Nothing>()
    data class Success<T>(val data: T): BasketScreenState<T>()
    data class Failed(val error: Exception): BasketScreenState<Nothing>()
    
}