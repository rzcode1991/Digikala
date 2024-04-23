package com.example.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.DigiClubScoreSection
import com.example.digikala.ui.components.WaitText
import com.example.digikala.ui.screens.basket.BasketScreenState
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.BasketViewModel
import com.example.digikala.viewModel.CheckoutViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
){

    val shippingCost = 49000

    val scope = rememberCoroutineScope()

    val listState = rememberLazyListState()

    val currentCartPriceDetail by basketViewModel.currentCartPriceDetail.collectAsState()

    var allCurrentCartItems by remember {
        mutableStateOf<List<CartItem>>(emptyList())
    }

    val allCurrentCartItemsState by basketViewModel.allCurrentCartItems.collectAsState()

    when(allCurrentCartItemsState){
        is BasketScreenState.Success -> {
            allCurrentCartItems = (allCurrentCartItemsState as BasketScreenState.Success<List<CartItem>>).data

            if (allCurrentCartItems.isNotEmpty()) {

                val modalBottomSheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded},
                    skipHalfExpanded = false
                )

                ModalBottomSheetLayout(
                    sheetState = modalBottomSheetState,
                    sheetShape = RoundedCornerShape(
                        topStart = MaterialTheme.spacing.semiMedium,
                        topEnd = MaterialTheme.spacing.semiMedium
                    ),
                    sheetContent = {
                        DeliveryTimeBottomSheet(
                            scope = scope,
                            bottomSheetState = modalBottomSheetState
                        )
                    }
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            state = listState
                        ){

                            item {
                                CheckoutTopSection(navController)
                            }
                            item {
                                CheckoutAddressSection()
                            }
                            item {
                                CheckoutSendingTimeSection(
                                    shippingCost = shippingCost.toString(),
                                    allCurrentCartItems = allCurrentCartItems,
                                    currentCartPriceDetail = currentCartPriceDetail,
                                    selectedDay = checkoutViewModel.selectedDay,
                                    onClick = {
                                        checkoutViewModel.onEvent(
                                            CheckoutBottomSheetState.OnCloseOpenBottomSheet(
                                                scope = scope,
                                                bottomSheetState = modalBottomSheetState
                                            )
                                        )
                                    }
                                )
                            }
                            item {
                                CheckoutFactorTxtSection()
                            }
                            item {
                                CheckoutPriceDetailSection(
                                    cartPriceDetail = currentCartPriceDetail
                                )
                            }
                            item {
                                CheckoutShippingCostSection(
                                    shippingCost = shippingCost.toString(),
                                    totalItemsPrice = currentCartPriceDetail.totalFinalPrice.toString()
                                )
                            }
                            item {
                                val digiScore = currentCartPriceDetail.totalFinalPrice / 100_000
                                DigiClubScoreSection(
                                    score = digiScore
                                )

                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(6.dp)
                                        .alpha(0.4f),
                                    color = Color.LightGray
                                )
                            }

                        }

                        CheckoutContinueBuyingSection(
                            isTimeSelected = checkoutViewModel.isTimeSelected,
                            finalPrice = currentCartPriceDetail.totalFinalPrice + shippingCost.toLong(),
                            navController = navController,
                            onClick = {
                                if(!checkoutViewModel.isTimeSelected){
                                    checkoutViewModel.onEvent(
                                        CheckoutBottomSheetState.OnCloseOpenBottomSheet(
                                            scope = scope,
                                            bottomSheetState = modalBottomSheetState
                                        )
                                    )
                                    scope.launch {
                                        listState.animateScrollToItem(index = 2)
                                    }
                                }else{
                                   // TODO navigate to next screen
                                }
                            }
                        )

                    }

                }
            } else {
                navController.navigate(Screen.Basket.route) {
                    popUpTo(Screen.Basket.route){
                        inclusive = true
                    }
                }
            }

        }
        is BasketScreenState.Loading -> {
            WaitText()
        }
        is BasketScreenState.Failed -> {
            Log.e("BasketScreenState failed", "allCurrentCartItems CheckoutScreen failed")
        }
    }


}
