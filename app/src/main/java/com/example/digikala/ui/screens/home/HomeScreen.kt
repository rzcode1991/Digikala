package com.example.digikala.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.utils.Constants
import com.example.digikala.utils.LocaleUtils
import com.example.digikala.viewModel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController
){

    Home(navController)

}


@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
){

    LocaleUtils.setLocale(LocalContext.current, Constants.USER_LANGUAGE)

    LaunchedEffect(true){
        getDataFromServer(viewModel)
    }

    SwipeRefreshSection(viewModel, navController)

}


@Composable
fun SwipeRefreshSection(
    viewModel: HomeViewModel,
    navController: NavHostController
){
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)
    val refreshScope = rememberCoroutineScope()
    SwipeRefresh(
        state = refreshState,
        onRefresh = {
            refreshScope.launch {
                getDataFromServer(viewModel)
            }
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ){

            item{
                SearchBarSection()
            }
            item{
                TopSliderSection()
            }
            item{
                ShowCaseSection(navController)
            }
            item{
                SpecialOffersSection()
            }
            item{
                ProposalCardsSection()
            }
            item{
                SpecialSupermarketOffersSection()
            }
            item {
                MainCategoriesSection()
            }
            item {
                CenterBannerSection(1)
            }
            item {
                BestSellerProductsSection()
            }
            item {
                CenterBannerSection(2)
            }
            item {
                MostVisitedProductsSection()
            }
            item {
                CenterBannerSection(3)
            }
            item {
                MostFavoriteProductsSection()
            }
            item {
                CenterBannerSection(4)
            }
            item {
                MostDiscountedProductsSection()
            }
            item {
                CenterBannerSection(5)
            }

        }

    }
}

private suspend fun getDataFromServer(viewModel: HomeViewModel){
    viewModel.getAllDataFromServer()
}