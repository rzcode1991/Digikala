package com.example.digikala.ui.screens.checkout

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.checkout.OrderRequest
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.DigiClubScoreSection
import com.example.digikala.ui.components.WaitText
import com.example.digikala.ui.screens.basket.BasketScreenState
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants
import com.example.digikala.utils.Constants.USER_ADDRESS
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.viewModel.BasketViewModel
import com.example.digikala.viewModel.CheckoutViewModel
import com.example.digikala.viewModel.DataStoreViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel()
){

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val shippingCost = 49000

    var isButtonLoading by remember {
        mutableStateOf(false)
    }

    var orderId by remember {
        mutableStateOf("")
    }

    val listState = rememberLazyListState()

    val currentCartPriceDetail by basketViewModel.currentCartPriceDetail.collectAsState()

    var allCurrentCartItems by remember {
        mutableStateOf<List<CartItem>>(emptyList())
    }

    val allCurrentCartItemsState by basketViewModel.allCurrentCartItems.collectAsState()

    val newOrderRequest = OrderRequest(
        token = Constants.USER_TOKEN,
        orderTotalPrice = currentCartPriceDetail.totalFinalPrice + shippingCost.toLong(),
        orderTotalDiscount = currentCartPriceDetail.totalDiscountPrice,
        orderAddress = USER_ADDRESS,
        orderUserName = USER_NAME,
        orderUserPhone = USER_PHONE,
        orderDate = checkoutViewModel.selectedDay.toString(),
        orderProducts = allCurrentCartItems
    )

    LaunchedEffect(Dispatchers.Main){
        checkoutViewModel.newOrderResponseResult.collectLatest { newOrderResponseResult ->
            when(newOrderResponseResult){
                is NetworkResult.Success -> {
                    orderId = newOrderResponseResult.data ?: ""
                    if (orderId.isNotEmpty()){
                        val orderPrice = (currentCartPriceDetail.totalFinalPrice + shippingCost.toLong()).toString()
                        dataStore.saveOrderId(orderId)
                        dataStore.saveOrderPrice(orderPrice)
                        navController.navigate(Screen.ConfirmPurchase.withArgs(orderId, orderPrice))
                    }
                    isButtonLoading = false
                }
                is NetworkResult.Loading -> {
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.network_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("newOrderResponseResult err", newOrderResponseResult.message.toString())
                    isButtonLoading = false
                }
            }
        }
    }

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
                                    navController = navController,
                                    onSelectTimeTxtClick = {
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
                            isButtonLoading = isButtonLoading,
                            finalPrice = currentCartPriceDetail.totalFinalPrice + shippingCost.toLong(),
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
                                    isButtonLoading = true
                                    scope.launch {
                                        checkoutViewModel.setNewOrder(newOrderRequest)
                                    }
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
