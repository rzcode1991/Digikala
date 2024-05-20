package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.components.NetworkErrorLoading
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MostFavoriteProductsSection(
    scope: CoroutineScope,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
){

    var mostFavoriteProducts by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var isError by remember {
        mutableStateOf(false)
    }

    val mostFavoriteProductsResult by viewModel.mostFavoriteProducts.collectAsState()
    when(mostFavoriteProductsResult){
        is NetworkResult.Success -> {
            mostFavoriteProducts = mostFavoriteProductsResult.data ?: emptyList()
            isLoading = false
            isError = false
        }
        is NetworkResult.Error -> {
            Log.e("mostFavoriteProductsResult Error", mostFavoriteProductsResult.message.toString())
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
            .padding(MaterialTheme.spacing.small)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.most_favorite_products),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkText
            )

            Text(
                text = stringResource(id = R.string.see_all),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkCyan
            )

        }

        LazyRow{

            if (isLoading){
                item {
                    MyLoading(
                        height = 320.dp,
                        isDark = true,
                        modifier = Modifier
                            .width(170.dp)
                    )
                }
            }else if (isError){
                item {
                    NetworkErrorLoading(height = 320.dp) {
                        scope.launch {
                            viewModel.getAllDataFromServer()
                        }
                    }
                }
            }else{
                items(mostFavoriteProducts){item ->
                    MostFavoriteProductsItem(item){
                        navController.navigate(Screen.ProductDetail.withArgs(item._id))
                    }
                }
            }

            item {
                MostFavoriteProductsShowMoreItem()
            }

        }

    }


}