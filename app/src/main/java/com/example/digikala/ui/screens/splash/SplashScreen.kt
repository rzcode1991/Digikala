package com.example.digikala.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.Loading3Dots
import com.example.digikala.ui.theme.splashBg
import com.example.digikala.utils.Constants.STATUS_FROM_CALLBACK
import com.example.digikala.viewModel.DataStoreViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel()
){

    Splash()
    LaunchedEffect(true){
        delay(2500)
        if (STATUS_FROM_CALLBACK.isEmpty()){
            navController.navigate(Screen.Home.route){
                popUpTo(Screen.Splash.route){
                    inclusive = true
                }
            }
        }else{
            val orderId = dataStore.getOrderId().toString()
            val orderPrice = dataStore.getOrderPrice().toString()
            navController.navigate(Screen.ConfirmPurchase.withArgs(orderId, orderPrice)){
                popUpTo(Screen.Splash.route){
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun Splash(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.splashBg),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.digi_logo),
            contentDescription = "",
            modifier = Modifier.size(250.dp)
            )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(100.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_txt_white),
                contentDescription = "",
                modifier = Modifier.height(30.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Loading3Dots(false)
        }
    }
}