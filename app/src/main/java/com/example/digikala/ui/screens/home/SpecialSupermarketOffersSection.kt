package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.home.SpecialOfferItem
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.digikalaLightGreen
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.utils.Constants
import com.example.digikala.viewModel.HomeViewModel

@Composable
fun SpecialSupermarketOffersSection(
    viewModel: HomeViewModel = hiltViewModel()
){

    var specialSuperMarketList by remember {
        mutableStateOf<List<SpecialOfferItem>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val specialSupermarketResult by viewModel.specialSupermarketOffers.collectAsState()
    when(specialSupermarketResult){
        is NetworkResult.Success -> {
            specialSuperMarketList = specialSupermarketResult.data ?: emptyList()
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
        is NetworkResult.Error -> {
            isLoading = false
            Log.e(":::specialSupermarketResult err:::", specialSupermarketResult.message.toString())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.digikalaLightGreen)
    ) {

        LazyRow(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.digikalaLightGreen)
        ){

            item {
                AmazingOfferCard(
                    topImage = AmazingSupermarketOfferLogoByLang(),
                    bottomImage = painterResource(id = R.drawable.fresh)
                )
            }

            if (isLoading){
                item {
                    MyLoading(
                        height = 375.dp,
                        isDark = true,
                        modifier = Modifier
                            .width(170.dp)
                    )
                }
            }else{
                items(specialSuperMarketList){item ->
                    AmazingItem(item)
                }
            }

            item {
                AmazingShowMoreItem()
            }

        }

    }


}

@Composable
private fun AmazingSupermarketOfferLogoByLang(): Painter {
    return if (Constants.USER_LANGUAGE == Constants.PERSIAN_LANG){
        painterResource(id = R.drawable.supermarketamazings)
    }else{
        painterResource(id = R.drawable.amazing_en)
    }
}