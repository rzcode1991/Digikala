package com.example.digikala.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.BasketViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuggestionForYouSection(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel()
){

    LaunchedEffect(true){
        viewModel.getSuggestedItems()
    }

    var allProductsList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val allProductsResult by viewModel.allProducts.collectAsState()
    when(allProductsResult){
        is NetworkResult.Success -> {
            allProductsList = allProductsResult.data ?: emptyList()
            isLoading = false
        }
        is NetworkResult.Error -> {
            Log.e("allProductsList err", allProductsResult.message.toString())
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(id = R.string.suggestion_for_you),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            textAlign = TextAlign.Start
        )

        if (isLoading){
            MyLoading(
                height = 100.dp,
                isDark = true
            )
        }else{
            FlowRow(
                maxItemsInEachRow = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Start
            ) {

                for (item in allProductsList){
                    SuggestedProductItemView(
                        item = item,
                        onAddClick = {
                            viewModel.addCartItem(
                                CartItem(
                                    it._id,
                                    it.discountPercent,
                                    it.image,
                                    it.name,
                                    it.price,
                                    it.seller,
                                    1,
                                    CartStatus.CURRENT_CART
                                )
                            )
                        },
                        onItemClick = {
                            navController.navigate(Screen.ProductDetail.withArgs(item._id))
                        }
                    )
                }

            }
        }

    }


}