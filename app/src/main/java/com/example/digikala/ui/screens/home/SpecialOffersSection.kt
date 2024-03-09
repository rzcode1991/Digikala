package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.home.SpecialOfferItem
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.viewModel.HomeViewModel

@Composable
fun SpecialOffersSection(
    viewModel: HomeViewModel = hiltViewModel()
){

    var specialOffers by remember {
        mutableStateOf<List<SpecialOfferItem>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val specialOffersResult by viewModel.specialOffers.collectAsState()
    when(specialOffersResult){
        is NetworkResult.Success -> {
            specialOffers = specialOffersResult.data ?: emptyList()
            isLoading = false
        }
        is NetworkResult.Error -> {
            Log.e("specialOffersNetwork Error", specialOffersResult.message.toString())
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.digikalaLightRed)
    ) {

        LazyRow(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.digikalaLightRed)
        ){

            item {
                AmazingOfferCard(
                    topImage = painterResource(id = R.drawable.amazings),
                    bottomImage = painterResource(id = R.drawable.box)
                )
            }

            items(specialOffers){item ->
                AmazingItem(item)
            }

            item {
                AmazingShowMoreItem()
            }

        }

    }



}