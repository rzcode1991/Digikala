package com.example.digikala.data.network

import com.example.digikala.data.model.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<ResponseResult<T>>
    ): NetworkResult<T> =
        withContext(Dispatchers.IO){
            try {
                val response = apiCall()
                if (response.isSuccessful){
                    val body = response.body()
                    body?.let {
                        return@withContext NetworkResult.Success(
                            message = it.message,
                            data = it.data
                        )
                    }
                }
                return@withContext error("code: ${response.code()}, message: ${response.message()}")
            }catch (e: Exception){
                return@withContext error(e.message ?: e.toString())
            }
        }


    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("api call failed: $errorMessage")


}