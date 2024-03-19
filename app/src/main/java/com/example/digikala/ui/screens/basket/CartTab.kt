package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.viewModel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CartTab(
    viewModel: BasketViewModel = hiltViewModel()
){

    var allCurrentCartItems by remember {
        mutableStateOf<List<CartItem>>(emptyList())
    }

    LaunchedEffect(true){
        viewModel.allCurrentCartItems.collectLatest { list ->
            allCurrentCartItems = list
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                bottom = 80.dp
            )
    ){

        if (allCurrentCartItems.isEmpty()){
            item {
                EmptyCartSection()
            }
            item {
                SuggestionForYouSection()
            }
        }else{
            items(allCurrentCartItems){item ->
                CartItemView(item)
            }
        }

    }

}