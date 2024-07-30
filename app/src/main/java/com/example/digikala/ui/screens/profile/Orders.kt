package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.profile.MyOrdersIcons
import com.example.digikala.ui.components.TopSectionWithBackArrowAndText
import com.example.digikala.ui.screens.basket.BadgeForTab
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.CANCELED_ORDER
import com.example.digikala.utils.Constants.DELIVERED_ORDER
import com.example.digikala.utils.Constants.PROCESSING_ORDER
import com.example.digikala.utils.Constants.RETURNED_ORDER
import com.example.digikala.utils.Constants.WAIT_FOR_PAY_ORDER
import com.example.digikala.viewModel.CheckoutViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Orders(
    navController: NavHostController,
    clickSourceId: String,
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
){

    LaunchedEffect(Unit) {
        checkoutViewModel.getAllWaitingForPayOrders()
        checkoutViewModel.getAllProcessingOrders()
    }

    val allWaitingForPayOrders by checkoutViewModel.allWaitingForPayOrders.collectAsState()
    val allProcessingOrders by checkoutViewModel.allProcessingOrders.collectAsState()

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(clickSourceId){
        when(clickSourceId){
            WAIT_FOR_PAY_ORDER -> {
                selectedTabIndex = 0
            }
            PROCESSING_ORDER -> {
                selectedTabIndex = 1
            }
            DELIVERED_ORDER -> {
                selectedTabIndex = 2
            }
            CANCELED_ORDER -> {
                selectedTabIndex = 3
            }
            RETURNED_ORDER -> {
                selectedTabIndex = 4
            }
        }
    }


    val myOrdersTabsList = listOf(
        MyOrdersIcons(
            name = stringResource(id = R.string.unpaid),
            image = rememberAsyncImagePainter(model = R.drawable.digi_unpaid),
            id = WAIT_FOR_PAY_ORDER,
            hasNews = allWaitingForPayOrders.isNotEmpty(),
            notifCount = allWaitingForPayOrders.size,
            ordersList = allWaitingForPayOrders
        ),
        MyOrdersIcons(
            name = stringResource(id = R.string.processing),
            image = rememberAsyncImagePainter(model = R.drawable.digi_processing),
            id = PROCESSING_ORDER,
            hasNews = allProcessingOrders.isNotEmpty(),
            notifCount = allProcessingOrders.size,
            ordersList = allProcessingOrders
        ),
        MyOrdersIcons(
            name = stringResource(id = R.string.delivered),
            image = rememberAsyncImagePainter(model = R.drawable.digi_delivered),
            id = DELIVERED_ORDER
        ),
        MyOrdersIcons(
            name = stringResource(id = R.string.canceled),
            image = rememberAsyncImagePainter(model = R.drawable.digi_cancel),
            id = CANCELED_ORDER
        ),
        MyOrdersIcons(
            name = stringResource(id = R.string.returned),
            image = rememberAsyncImagePainter(model = R.drawable.digi_returned),
            id = RETURNED_ORDER
        )
    )

    val pagerState = rememberPagerState {
        myOrdersTabsList.size
    }

    LaunchedEffect(selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress){
        if (!pagerState.isScrollInProgress){
            selectedTabIndex = pagerState.currentPage
        }
    }

    Scaffold(
        topBar = {
            TopSectionWithBackArrowAndText(
                navController = navController,
                title = stringResource(id = R.string.my_orders)
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.bottomBarColor,
                indicator = { tabPositionLine ->
                    MyIndicator(tabPositionLine, selectedTabIndex)
                }
            ){

                myOrdersTabsList.forEachIndexed { index, item ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                        },
                        selectedContentColor = MaterialTheme.colorScheme.digikalaRed,
                        unselectedContentColor = Color.Gray,
                        content = {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.semiMedium),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = item.name,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Medium
                                )

                                if (item.hasNews){

                                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                                    BadgeForTab(
                                        selectedIndex = selectedTabIndex,
                                        index = index,
                                        count = item.notifCount
                                    )
                                }

                            }

                        }
                    )
                }

            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {pageIndex ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    if (myOrdersTabsList[pageIndex].ordersList.isEmpty()){

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = MaterialTheme.spacing.large,
                                    horizontal = MaterialTheme.spacing.medium
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = stringResource(id = R.string.no_orders_here),
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Medium
                            )

                        }

                    }else{

                        OrderItemView(
                            orders = myOrdersTabsList[pageIndex].ordersList,
                            navController = navController
                        )

                    }

                }

            }

        }

    }


}

@Composable
private fun MyIndicator(
    tabPositionLine: List<TabPosition>,
    selectedTabIndex: Int
){
    Column(
        modifier = Modifier
            .tabIndicatorOffset(tabPositionLine[selectedTabIndex])
            .height(3.dp)
            .background(MaterialTheme.colorScheme.digikalaRed)
    ) {
    }
}