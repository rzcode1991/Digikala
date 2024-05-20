package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.CenterBannerItem
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.components.NetworkErrorLoading
import com.example.digikala.viewModel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CenterBannerSection(
    scope: CoroutineScope,
    bannerNumber: Int,
    viewModel: HomeViewModel = hiltViewModel()
){

    var centerBannersList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var isError by remember {
        mutableStateOf(false)
    }

    val centerBannersResult by viewModel.centerBanners.collectAsState()
    when (centerBannersResult) {
        is NetworkResult.Success -> {
            centerBannersList = centerBannersResult.data ?: emptyList()
            isLoading = false
            isError = false
        }
        is NetworkResult.Error -> {
            Log.e(":::centerBannersResult error:::", centerBannersResult.message.toString())
            isLoading = false
            isError = true
        }
        is NetworkResult.Loading -> {
            isLoading = true
            isError = false
        }
    }

    if (centerBannersList.isNotEmpty() && !isLoading){
        val bannerItem = centerBannersList[bannerNumber - 1]
        CenterBannerItem(bannerItem.image)
    }else if (isError){
        NetworkErrorLoading(height = 170.dp) {
            scope.launch {
                viewModel.getAllDataFromServer()
            }
        }
    }else{
        MyLoading(
            height = 170.dp,
            isDark = true
        )
    }



}