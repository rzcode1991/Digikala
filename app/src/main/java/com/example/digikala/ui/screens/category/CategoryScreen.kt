package com.example.digikala.ui.screens.category

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
import com.example.digikala.ui.components.SearchBarSection
import com.example.digikala.utils.Constants.USER_LANGUAGE
import com.example.digikala.utils.LocaleUtils
import com.example.digikala.viewModel.CategoryViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(
    navController: NavHostController
){

    Category(navController)

}

@Composable
private fun Category(
    navController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel()
){

    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

    LaunchedEffect(true){
        getAllDataFromServer(viewModel)
    }

    SwipeRefreshSection(navController, viewModel)

}


@Composable
private fun SwipeRefreshSection(
    navController: NavHostController,
    viewModel: CategoryViewModel
){

    val refreshState = rememberSwipeRefreshState(isRefreshing = false)
    val refreshScope = rememberCoroutineScope()

    SwipeRefresh(
        state = refreshState,
        onRefresh = {
            refreshScope.launch {
                getAllDataFromServer(viewModel)
            }
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ){

            item {
                SearchBarSection()
            }
            item {
                SubCategorySection()
            }

        }

    }

}


private suspend fun getAllDataFromServer(
    viewModel: CategoryViewModel
){
    viewModel.getAllDataFromServer()
}