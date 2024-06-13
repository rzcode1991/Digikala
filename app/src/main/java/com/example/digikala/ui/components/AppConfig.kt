package com.example.digikala.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.utils.Constants.PERSIAN_LANG
import com.example.digikala.utils.Constants.USER_ID
import com.example.digikala.utils.Constants.USER_LANGUAGE
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.utils.Constants.USER_PASSWORD
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.DataStoreViewModel
import com.example.digikala.viewModel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppConfig(
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
){

    getDataStoreVariables(dataStoreViewModel)

    if (!dataStoreViewModel.getUserPhone().isNullOrEmpty()){
        LaunchedEffect(true){
            profileViewModel.refreshToken(USER_PHONE, USER_PASSWORD)
        }
    }

    LaunchedEffect(Dispatchers.Main){
        profileViewModel.loginResponseResult.collectLatest { loginResponseResult->
            when(loginResponseResult){
                is NetworkResult.Success -> {
                    if (loginResponseResult.success == true &&
                        loginResponseResult.data?.token?.isNotBlank() == true
                    ){
                        loginResponseResult.data.let { loginResponse ->
                            dataStoreViewModel.saveUserId(loginResponse.id)
                            dataStoreViewModel.saveUserPassword(USER_PASSWORD)
                            dataStoreViewModel.saveUserPhone(loginResponse.phone)
                            dataStoreViewModel.saveUserToken(loginResponse.token)
                            dataStoreViewModel.saveUserName(loginResponse.name ?: USER_NAME)

                            getDataStoreVariables(dataStoreViewModel)
                        }
                    }
                }
                else -> {}
            }
        }
    }

}

private fun getDataStoreVariables(
    dataStoreViewModel: DataStoreViewModel
){
    USER_LANGUAGE = dataStoreViewModel.getUserLanguage() ?: PERSIAN_LANG
    dataStoreViewModel.saveUserLanguage(USER_LANGUAGE)

    USER_PHONE = dataStoreViewModel.getUserPhone() ?: ""
    USER_ID = dataStoreViewModel.getUserId() ?: "USER_ID"
    USER_TOKEN = dataStoreViewModel.getUserToken() ?: ""
    USER_PASSWORD = dataStoreViewModel.getUserPassword() ?: ""
    USER_NAME = dataStoreViewModel.getUserName() ?: "user_name"
}