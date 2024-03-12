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
import com.example.digikala.viewModel.HomeViewModel

@Composable
fun CenterBannerSection(
    bannerNumber: Int,
    viewModel: HomeViewModel = hiltViewModel()
){

    var centerBannersList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val centerBannersResult by viewModel.centerBanners.collectAsState()
    when (centerBannersResult) {
        is NetworkResult.Success -> {
            centerBannersList = centerBannersResult.data ?: emptyList()
            isLoading = false
        }
        is NetworkResult.Error -> {
            Log.e(":::centerBannersResult error:::", centerBannersResult.message.toString())
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
    }

    if (centerBannersList.isNotEmpty() && !isLoading){
        val bannerItem = centerBannersList[bannerNumber - 1]
        CenterBannerItem(bannerItem.image)
    }else{
        MyLoading(
            height = 170.dp,
            isDark = true
        )
    }



}