package com.example.digikala.repository

import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: ProfileApiInterface
): BaseApiResponse() {

    //

}