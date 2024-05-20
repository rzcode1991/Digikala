package com.example.digikala.ui.screens.productDetails

sealed class ProductDetailsScreenState<out T> {

    data object Loading: ProductDetailsScreenState<Nothing>()

    data class Success<T>(val data: T): ProductDetailsScreenState<T>()

    data class Error(val error: Exception): ProductDetailsScreenState<Nothing>()

}