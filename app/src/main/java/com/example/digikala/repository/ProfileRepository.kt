package com.example.digikala.repository

import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.model.profile.userInfo.UserInfoRequest
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.data.network.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: ProfileApiInterface
): BaseApiResponse() {

    suspend fun logIn(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
            api.logIn(loginRequest)
        }

    suspend fun setUserName(userInfoRequest: UserInfoRequest): NetworkResult<String> =
        safeApiCall {
            api.setUserName(userInfoRequest)
        }

}