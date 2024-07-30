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
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.components.NetworkErrorLoading
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.utils.Constants
import com.example.digikala.viewModel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SpecialOffersSection(
    scope: CoroutineScope,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
){

    var specialOffers by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var isError by remember {
        mutableStateOf(false)
    }

    val specialOffersResult by viewModel.specialOffers.collectAsState()
    when(specialOffersResult){
        is NetworkResult.Success -> {
            specialOffers = specialOffersResult.data ?: emptyList()
            isLoading = false
            isError = false
        }
        is NetworkResult.Error -> {
            Log.e("specialOffersNetwork Error", specialOffersResult.message.toString())
            isLoading = false
            isError = true
        }
        is NetworkResult.Loading -> {
            isLoading = true
            isError = false
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
                    topImage = AmazingOfferLogoByLang(),
                    bottomImage = painterResource(id = R.drawable.box)
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
            }else if (isError){
                item {
                    NetworkErrorLoading(height = 375.dp) {
                        scope.launch {
                            viewModel.getAllDataFromServer()
                        }
                    }
                }
            }else{
                items(specialOffers){item ->
                    AmazingItem(item, navController)
                }
            }

            item {
                AmazingShowMoreItem()
            }

        }

    }



}

@Composable
private fun AmazingOfferLogoByLang(): Painter {
    return if (Constants.USER_LANGUAGE == Constants.PERSIAN_LANG){
        painterResource(id = R.drawable.amazings)
    }else{
        painterResource(id = R.drawable.amazing_en)
    }
}