package com.example.digikala.data.network

sealed class NetworkResult<T>(
    val message: String? = null,
    val data: T? = null,
    val success: Boolean? = null
) {

    class Success<T>(message: String, data: T, success: Boolean): NetworkResult<T>(message, data, success)
    class Error<T>(message: String, data: T? = null): NetworkResult<T>(message, data)
    class Loading<T>: NetworkResult<T>()

}