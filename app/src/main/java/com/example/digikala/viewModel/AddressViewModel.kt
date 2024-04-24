package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.basket.Address
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

    init {
        getUserAddressList(USER_TOKEN)
    }

    private fun getUserAddressList(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            userAddressList.emit(repository.getUserAddressList(token))
        }
    }

}