package com.example.digikala.ui.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.viewModel.DataStoreViewModel
import com.example.digikala.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    viewModel: ProfileViewModel = hiltViewModel()
){

    when(viewModel.screenState){
        ProfileScreenState.LOGIN_SCREEN -> {
            LoginScreen()
        }
        ProfileScreenState.REGISTER_SCREEN -> {
            RegisterScreen()
        }
        ProfileScreenState.PROFILE_SCREEN -> {
            Profile()
        }
    }

}

@Composable
fun Profile(){
    Text(text = "profile")
}