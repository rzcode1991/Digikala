package com.example.digikala.ui.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.utils.Constants.USER_LANGUAGE
import com.example.digikala.viewModel.DataStoreViewModel

@Composable
fun AppConfig(
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
){

    getDataStoreVariables(dataStoreViewModel)

}

private fun getDataStoreVariables(
    dataStoreViewModel: DataStoreViewModel
){
    USER_LANGUAGE = dataStoreViewModel.getUserLanguage()
    dataStoreViewModel.saveUserLanguage(USER_LANGUAGE)
}