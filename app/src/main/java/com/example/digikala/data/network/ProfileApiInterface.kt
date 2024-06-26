package com.example.digikala.data.network

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.model.profile.userInfo.UserInfoRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileApiInterface {

    @POST("v1/login")
    suspend fun logIn(
        @Body loginRequest: LoginRequest
    ): Response<ResponseResult<LoginResponse>>

    @POST("v1/setUserName")
    suspend fun setUserName(
        @Body userInfoRequest: UserInfoRequest
    ): Response<ResponseResult<String>>

}