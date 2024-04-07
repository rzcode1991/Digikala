package com.example.digikala.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.repository.ProfileRepository
import com.example.digikala.ui.screens.profile.ProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {

    var screenState by mutableStateOf(ProfileScreenState.LOGIN_SCREEN)

    var phoneEmailInput by mutableStateOf("")

    var passwordInputRegister by mutableStateOf("")

    val loginResponseResult = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())

    fun login(){
        viewModelScope.launch {
            val loginRequest = LoginRequest(
                phone = phoneEmailInput,
                password = passwordInputRegister
            )
            loginResponseResult.emit(repository.logIn(loginRequest))
        }
    }

}