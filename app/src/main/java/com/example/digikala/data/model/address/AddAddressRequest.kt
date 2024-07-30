package com.example.digikala.data.model.address

data class AddAddressRequest(
    val token: String,
    val address: String,
    val name: String,
    val phone: String,
    val postalCode: String
)
