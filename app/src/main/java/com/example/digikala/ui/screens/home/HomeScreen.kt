package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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
        ){

            item{
                SearchBarSection()
            }
            item{
                TopSliderSection()
            }

        }

    }
}

private suspend fun getDataFromServer(viewModel: HomeViewModel){
    viewModel.getSlider()
}