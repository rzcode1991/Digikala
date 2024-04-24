package com.example.digikala.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.utils.Constants.USER_ID
import com.example.digikala.utils.Constants.USER_LANGUAGE
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

    if (USER_PHONE.isNotEmpty()){
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
    USER_LANGUAGE = dataStoreViewModel.getUserLanguage()
    dataStoreViewModel.saveUserLanguage(USER_LANGUAGE)

    USER_PHONE = dataStoreViewModel.getUserPhone().orEmpty()
    USER_ID = dataStoreViewModel.getUserId().orEmpty()
    USER_TOKEN = dataStoreViewModel.getUserToken().orEmpty()
    USER_PASSWORD = dataStoreViewModel.getUserPassword().orEmpty()
}