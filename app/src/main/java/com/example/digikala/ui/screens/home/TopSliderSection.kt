package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.theme.LocalShape
import com.example.digikala.ui.theme.LocalSpacing
import com.example.digikala.viewModel.HomeViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@Suppress("DEPRECATION")
@Composable
fun TopSliderSection(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var sliderList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val sliderResult by viewModel.slider.collectAsState()
    when (sliderResult) {
        is NetworkResult.Success -> {
            sliderList = sliderResult.data ?: emptyList()
            isLoading = false
        }
        is NetworkResult.Error -> {
            Log.e(":::slider error:::", sliderResult.message.toString())
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    horizontal = LocalSpacing.current.extraSmall,
                    vertical = LocalSpacing.current.small
                )
        ) {

            val pagerState = rememberPagerState()
            var imageUrl by remember {
                mutableStateOf("")
            }

            HorizontalPager(
                count = sliderList.size,
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = LocalSpacing.current.medium
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {pageIndex ->

                imageUrl = sliderList[pageIndex].image
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .apply (
                                block = fun ImageRequest.Builder.(){
                                    scale(Scale.FILL)
                                }
                            )
                            .build()
                    )
                    Image(
                        painter = painter,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(LocalSpacing.current.small)
                            .clip(LocalShape.current.medium)
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(LocalSpacing.current.semiLarge),
                        activeColor = Color.Black,
                        inactiveColor = Color.LightGray,
                        indicatorWidth = LocalSpacing.current.small,
                        indicatorHeight = LocalSpacing.current.small,
                        indicatorShape = CircleShape
                    )

                }

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