package com.example.digikala.repository

import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.BasketApiInterface
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val api: BasketApiInterface
): BaseApiResponse() {



}