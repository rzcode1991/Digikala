package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.components.NetworkErrorLoading
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.HomeViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@Composable
fun TopPagerSection(
    scope: CoroutineScope,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var sliderList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var isError by remember {
        mutableStateOf(false)
    }

    val sliderResult by viewModel.slider.collectAsState()
    when (sliderResult) {
        is NetworkResult.Success -> {
            sliderList = sliderResult.data ?: emptyList()
            isLoading = false
            isError = false
        }
        is NetworkResult.Error -> {
            Log.e(":::slider error:::", sliderResult.message.toString())
            isLoading = false
            isError = true
        }
        is NetworkResult.Loading -> {
            isLoading = true
            isError = false
        }
    }

    if (isLoading){
        MyLoading(
            height = 200.dp,
            isDark = true
        )
    }else if (isError){
        NetworkErrorLoading(height = 200.dp) {
            scope.launch {
                viewModel.getAllDataFromServer()
            }
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = MaterialTheme.spacing.extraSmall,
                        vertical = MaterialTheme.spacing.small
                    )
            ) {

                val pagerState = rememberPagerState()
                var imageUrl by remember {
                    mutableStateOf("")
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ){

                    HorizontalPager(
                        count = sliderList.size,
                        state = pagerState,
                        contentPadding = PaddingValues(
                            horizontal = MaterialTheme.spacing.medium
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                    ) {pageIndex ->

                        imageUrl = sliderList[pageIndex].image
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {

                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = imageUrl,
                                    error = painterResource(id = R.drawable.loading_placeholder)
                                ),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(MaterialTheme.spacing.small)
                                    .clip(MaterialTheme.roundedShape.medium)
                                    .fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )

                        }

                    }

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(MaterialTheme.spacing.semiLarge),
                        activeColor = Color.Black,
                        inactiveColor = Color.LightGray,
                        indicatorWidth = MaterialTheme.spacing.small,
                        indicatorHeight = MaterialTheme.spacing.small,
                        indicatorShape = CircleShape
                    )

                }

                LaunchedEffect(key1 = pagerState.currentPage){
                    delay(6000)
                    var newPage = pagerState.currentPage + 1
                    if (newPage > sliderList.size - 1) newPage = 0
                    pagerState.scrollToPage(newPage)
                }

            }

        }
    }



}