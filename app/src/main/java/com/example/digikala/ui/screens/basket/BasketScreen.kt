package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.BasketViewModel

@Composable
fun BasketScreen(
    navController: NavHostController
){

    Basket(navController)

}

@Composable
fun Basket(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel()
){

    var selectedTab by remember {
        mutableStateOf(0)
    }
    val tabsList = listOf(
        stringResource(id = R.string.cart),
        stringResource(id = R.string.next_cart_list)
    )

    Column {

        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium
                ),
            contentColor = MaterialTheme.colorScheme.digikalaRed,
            containerColor = MaterialTheme.colorScheme.bottomBarColor,
            indicator = { tabPositionLine ->

                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositionLine[selectedTab])
                        .height(3.dp)
                        .background(MaterialTheme.colorScheme.digikalaRed)
                ) {
                }

            }
        ) {

            tabsList.forEachIndexed{ index, item ->
                Tab(
                    selected = selectedTab == index,
                    onClick = {
                        selectedTab = index
                    },
                    text = {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.digikalaRed,
                    unselectedContentColor = Color.Gray
                )
            }

        }

        when(selectedTab){
            0 -> {
                CartTab()
            }
            1 -> {
                NextCartTab()
            }
        }

    }


}