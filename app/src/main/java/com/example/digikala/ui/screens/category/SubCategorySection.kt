package com.example.digikala.ui.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.category.CategoryDataItem
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.CategoryViewModel

@Composable
fun SubCategorySection(
    viewModel: CategoryViewModel = hiltViewModel()
){

    var isLoading by remember {
        mutableStateOf(false)
    }

    var toolList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var digitalList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var mobileList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var supermarketList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var fashionList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var nativeList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var toyList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var beautyList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var homeList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var bookList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }
    var sportList by remember {
        mutableStateOf<List<CategoryDataItem>>(emptyList())
    }

    val subCategoriesResult by viewModel.subCategories.collectAsState()

    when(subCategoriesResult){
        is NetworkResult.Success -> {
            toolList = subCategoriesResult.data?.tool ?: emptyList()
            digitalList = subCategoriesResult.data?.digital ?: emptyList()
            mobileList = subCategoriesResult.data?.mobile ?: emptyList()
            supermarketList = subCategoriesResult.data?.supermarket ?: emptyList()
            fashionList = subCategoriesResult.data?.fashion ?: emptyList()
            nativeList = subCategoriesResult.data?.native ?: emptyList()
            toyList = subCategoriesResult.data?.toy ?: emptyList()
            beautyList = subCategoriesResult.data?.beauty ?: emptyList()
            homeList = subCategoriesResult.data?.home ?: emptyList()
            bookList = subCategoriesResult.data?.book ?: emptyList()
            sportList = subCategoriesResult.data?.sport ?: emptyList()

            isLoading = false
        }

        is NetworkResult.Loading -> {
            isLoading = true
        }

        is NetworkResult.Error -> {
            isLoading = false
        }

    }

    if (isLoading){
        val config = LocalConfiguration.current
        MyLoading(config.screenHeightDp.dp, true)
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = MaterialTheme.spacing.small
                )
        ) {

            CategoryItemView(
                title = stringResource(id = R.string.industrial_tools_and_equipment),
                categoryList = toolList
            )
            CategoryItemView(
                title = stringResource(id = R.string.digital_goods),
                categoryList = digitalList
            )
            CategoryItemView(
                title = stringResource(id = R.string.mobile),
                categoryList = mobileList
            )
            CategoryItemView(
                title = stringResource(id = R.string.supermarket_product),
                categoryList = supermarketList
            )
            CategoryItemView(
                title = stringResource(id = R.string.fashion_and_clothing),
                categoryList = fashionList
            )
            CategoryItemView(
                title = stringResource(id = R.string.native_and_local_products),
                categoryList = nativeList
            )
            CategoryItemView(
                title = stringResource(id = R.string.toys_children_and_babies),
                categoryList = toyList
            )
            CategoryItemView(
                title = stringResource(id = R.string.beauty_and_health),
                categoryList = beautyList
            )
            CategoryItemView(
                title = stringResource(id = R.string.home_and_kitchen),
                categoryList = homeList
            )
            CategoryItemView(
                title = stringResource(id = R.string.books_and_stationery),
                categoryList = bookList
            )
            CategoryItemView(
                title = stringResource(id = R.string.sports_and_travel),
                categoryList = sportList
            )

        }
    }


}