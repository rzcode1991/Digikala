package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import com.example.digikala.utils.DigitHelper
import com.example.digikala.viewModel.HomeViewModel

@Composable
fun MostVisitedProductsSection(
    viewModel: HomeViewModel = hiltViewModel()
){

    var mostVisitedProducts by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val mostVisitedProductsResult by viewModel.mostVisitedProducts.collectAsState()
    when(mostVisitedProductsResult){
        is NetworkResult.Success -> {
            mostVisitedProducts = mostVisitedProductsResult.data ?: emptyList()
            isLoading = false
        }
        is NetworkResult.Error -> {
            Log.e("mostVisitedProducts Error", mostVisitedProductsResult.message.toString())
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small)
    ) {

        Text(
            text = stringResource(id = R.string.most_visited_products),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        if (isLoading){
            MyLoading(
                height = 250.dp,
                isDark = true
            )
        }else{
            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.spacing.medium
                    )
                    .height(250.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){

                itemsIndexed(mostVisitedProducts){index, item ->

                    ProductsHorizontalItemView(
                        name = item.name,
                        id = DigitHelper.engToFa((index+1).toString()),
                        imageUrl = item.image
                    )

                }

            }
        }

    }


}