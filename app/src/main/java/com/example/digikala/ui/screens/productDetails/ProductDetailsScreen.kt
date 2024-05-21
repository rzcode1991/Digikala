package com.example.digikala.ui.screens.productDetails

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.components.NetworkErrorLoading
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.ProductDetailsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    productId: String,
    productDetailsViewModel: ProductDetailsViewModel = hiltViewModel()
){

    val scope = rememberCoroutineScope()

    var productDetails by remember {
        mutableStateOf<ProductDetails?>(null)
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var isError by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true){
        getDataFromServer(
            viewModel = productDetailsViewModel,
            productId = productId
        )
        productDetailsViewModel.productDetailsResult.collectLatest { productDetailsResult ->
            when(productDetailsResult){
                is NetworkResult.Success -> {
                    productDetails = productDetailsResult.data
                    // TODO: check here
                    isLoading = false
                    isError = false
                }
                is NetworkResult.Loading -> {
                    isLoading = true
                    isError = false
                }
                is NetworkResult.Error -> {
                    isLoading = false
                    isError = true
                }
            }
        }
    }

    if (isLoading){
        MyLoading(height = 400.dp, isDark = true)
    }else if (isError){
        NetworkErrorLoading(height = 400.dp) {
            scope.launch {
                getDataFromServer(
                    viewModel = productDetailsViewModel,
                    productId = productId
                )
            }
        }
    }else{
        MyProductsDetailsScreen(
            navController = navController,
            productDetails = productDetails
        )
    }


}

@Composable
private fun MyProductsDetailsScreen(
    navController: NavHostController,
    productDetails: ProductDetails?
){

    if (productDetails != null){
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
                    ProductDetailsTopSection(navController = navController)
                }
                item {
                    ProductDetailPager(productDetails = productDetails)
                }
                item {
                    ProductName(productDetails.name)
                }
                item {
                    ScoreAndCommentsSection(productDetails = productDetails)
                }
                item {
                    ProductDetailAgreeCountSection(agreeCount = productDetails.agreeCount, agreePercent = productDetails.agreePercent)
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.spacing.medium,
                                vertical = MaterialTheme.spacing.biggerMedium
                            )
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray.copy(alpha = 0.6f))
                    )
                }
                item {
                    ColorSection(productDetails = productDetails)
                }
                item {
                    LargeSpacer()
                }
                item {
                    ProductSellerSection(productDetails = productDetails)
                }
                item {
                    WarrantySection()
                }
                item {
                    ShippingSection(productSeller = productDetails.seller)
                }
                item {
                    ProductDetailsDigiClubSection(price = productDetails.price)
                }
                item {
                    LargeSpacer()
                }
                item {
                    RowWithTextAndArrowIcon(title = stringResource(id = R.string.technical_details)){
                        // TODO onClick
                    }
                }
                item {
                    RowWithTextAndArrowIcon(title = stringResource(id = R.string.brief_description)){
                        navController.navigate(Screen.ProductDescription.withArgs(productDetails.name))
                    }
                }
                item {
                    ProductDetailsCommentsSection(productDetails = productDetails)
                }
                item {
                    val productName = productDetails.name
                    val productImage = Uri.encode(productDetails.imageSlider[0].image)
                    ProductDetailsAddCommentSection(){
                        navController.navigate(Screen.NewComment.withArgs(productName, productImage))
                    }
                }
                item {
                    // q&a
                }

            }

            AddToCartBottomSection(
                productDetails = productDetails,
                navHostController = navController
            )

        }
    }

}

@Composable
private fun ProductName(
    productName: String
){

    Text(
        text = productName,
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium),
        maxLines = 2,
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis
    )

}

private fun getDataFromServer(
    viewModel: ProductDetailsViewModel,
    productId: String
){
    viewModel.getProductById(productId)
}


@Composable
private fun LargeSpacer(){

    Spacer(
        modifier = Modifier
            .padding(
                top = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.small2
            )
            .fillMaxWidth()
            .height(MaterialTheme.spacing.semiSmall)
            .background(Color.LightGray.copy(alpha = 0.6f))
    )

}

@Composable
private fun RowWithTextAndArrowIcon(
    title: String,
    onClick: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.medium
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold
            )

            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.LightGray,
                modifier = Modifier
                    .size(30.dp)
            )

        }

        Spacer(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.small2
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray.copy(alpha = 0.6f))
        )

    }

}