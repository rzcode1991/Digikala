package com.example.digikala.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.ui.theme.darkText
import com.example.digikala.viewModel.BasketViewModel

@Composable
fun NextCartTab(
    viewModel: BasketViewModel = hiltViewModel()
){

    val allNextCartItemsState by viewModel.allNextCartItems.collectAsState(BasketScreenState.Loading)
    var allNextCartItems by remember {
        mutableStateOf<List<CartItem>>(emptyList())
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 80.dp
            )
    ){

        when(allNextCartItemsState){
            is BasketScreenState.Success -> {
                allNextCartItems = (allNextCartItemsState as BasketScreenState.Success<List<CartItem>>).data
                if (allNextCartItems.isEmpty()){
                    item {
                        EmptyNextCartSection()
                    }
                }else{
                    items(allNextCartItems){item ->
                        NextCartItemView(
                            item = item
                        )
                    }
                }
            }
            is BasketScreenState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(LocalConfiguration.current.screenHeightDp.dp - 80.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = stringResource(id = R.string.please_wait),
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.darkText
                        )

                    }
                }
            }
            is BasketScreenState.Failed -> {
                Log.e("BasketScreenState failed", "allNextCartItems failed")
            }
        }

    }


}