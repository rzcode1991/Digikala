package com.example.digikala.ui.screens.home

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
import com.example.digikala.R
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MostDiscountedProductsSection(
    viewModel: HomeViewModel = hiltViewModel()
){

    var mostDiscountedProducts by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val mostDiscountedProductsResult by viewModel.mostDiscountedProducts.collectAsState()
    when(mostDiscountedProductsResult){
        is NetworkResult.Success -> {
            mostDiscountedProducts = mostDiscountedProductsResult.data ?: emptyList()
            isLoading = false
        }
        is NetworkResult.Error -> {
            Log.e("mostDiscountedProductsResult Error", mostDiscountedProductsResult.message.toString())
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.extraSmall)
    ) {

        Text(
            text = stringResource(id = R.string.most_discounted_products),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            textAlign = TextAlign.Start
        )

        if (isLoading){
            MyLoading(
                height = 120.dp,
                isDark = true
            )
        }else{
            FlowRow(
                maxItemsInEachRow = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center
            ) {

                for (item in mostDiscountedProducts){
                    MostDiscountedProductsItem(item)
                }

            }
        }

    }


}