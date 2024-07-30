package com.example.digikala.repository

import com.example.digikala.data.model.address.AddAddressRequest
import com.example.digikala.data.model.address.Address
import com.example.digikala.data.network.AddressApiInterface
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.NetworkResult
import javax.inject.Inject

class AddressRepository @Inject constructor(
    private val api: AddressApiInterface
): BaseApiResponse() {

    suspend fun getUserAddressList(token: String): NetworkResult<List<Address>> =
        safeApiCall {
            api.getUserAddressList(token)
        }

    suspend fun saveUserAddress(addressRequest: AddAddressRequest): NetworkResult<String> =
        safeApiCall {
            api.saveUserAddress(addressRequest)
        }

}