package com.example.digikala.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.viewModel.DataStoreViewModel
import com.example.digikala.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    viewModel: ProfileViewModel = hiltViewModel()
){

    if (dataStoreViewModel.getUserToken().isNotBlank() && USER_PHONE.isNotEmpty()){
        Profile(navController = navController)
    }else{
        when(viewModel.screenState){
            ProfileScreenState.LOGIN_SCREEN -> {
                LoginScreen(navController = navController)
            }
            ProfileScreenState.REGISTER_SCREEN -> {
                RegisterScreen()
            }
            ProfileScreenState.PROFILE_SCREEN -> {
                Profile(navController = navController)
            }
        }
    }

}