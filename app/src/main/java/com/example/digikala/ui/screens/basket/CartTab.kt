package com.example.digikala.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.WaitText
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.BasketViewModel

@Composable
fun CartTab(
    viewModel: BasketViewModel = hiltViewModel(),
    navController: NavHostController
){

    val allCurrentCartItemsState by viewModel.allCurrentCartItems.collectAsState()

    var allCurrentCartItems by remember {
        mutableStateOf<List<CartItem>>(emptyList())
    }

    val currentCartPriceDetail by viewModel.currentCartPriceDetail.collectAsState()

    when(allCurrentCartItemsState){
        is BasketScreenState.Success -> {
            allCurrentCartItems = (allCurrentCartItemsState as BasketScreenState.Success<List<CartItem>>).data

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ){

                    item {
                        if (USER_TOKEN.isEmpty()){
                            GoToLoginSection(navController)
                        }
                    }

                    if (allCurrentCartItems.isEmpty()){
                        item {
                            EmptyCartSection()
                        }
                    }else{
                        item {
                            TopRowInBasketScreen(
                                title = stringResource(id = R.string.your_shopping_list),
                                counter = currentCartPriceDetail.totalCount
                            )
                        }
                        items(allCurrentCartItems){item ->
                            key(item.itemId) {
                                CartItemView(
                                    item = item,
                                    onItemClick = {
                                        navController.navigate(Screen.ProductDetail.withArgs(item.itemId))
                                    }
                                )
                            }
                        }

                        item {
                            CartPriceDetailSection(
                                cartPriceDetail = currentCartPriceDetail
                            )
                        }
                    }

                    item {
                        SuggestionForYouSection(navController = navController)
                    }
                }

                if (allCurrentCartItems.isNotEmpty()){
                    BasketContinueBuyingSection(
                        totalFinalPrice = currentCartPriceDetail.totalFinalPrice,
                        navHostController = navController
                    )
                }else{
                    Spacer(modifier = Modifier.height(80.dp))
                }

            }

        }
        is BasketScreenState.Loading -> {
            WaitText()
        }
        is BasketScreenState.Failed -> {
            Log.e("BasketScreenState failed", "allCurrentCartItems failed")
        }
    }

}