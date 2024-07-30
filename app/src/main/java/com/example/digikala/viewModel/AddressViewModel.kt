package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.address.AddAddressRequest
import com.example.digikala.data.model.address.Address
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.repository.AddressRepository
import com.example.digikala.utils.Constants.USER_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val repository: AddressRepository
): ViewModel() {

    val userAddressList = MutableStateFlow<NetworkResult<List<Address>>>(NetworkResult.Loading())
    val addAddressResult = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun getUserAddressList(){
        viewModelScope.launch(Dispatchers.IO) {
            userAddressList.emit(repository.getUserAddressList(USER_TOKEN))
        }
    }

    fun saveUserAddress(addressRequest: AddAddressRequest){
        viewModelScope.launch(Dispatchers.IO) {
            addAddressResult.emit(repository.saveUserAddress(addressRequest))
        }

    }

}