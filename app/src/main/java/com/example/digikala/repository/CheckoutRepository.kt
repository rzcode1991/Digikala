package com.example.digikala.repository

import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.CheckoutApiInterface
import javax.inject.Inject

class CheckoutRepository @Inject constructor(
    private val api: CheckoutApiInterface
): BaseApiResponse() {



}