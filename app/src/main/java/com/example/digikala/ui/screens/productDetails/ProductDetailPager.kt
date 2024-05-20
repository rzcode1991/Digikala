package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.ui.theme.spacing
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Suppress("DEPRECATION")
@Composable
fun ProductDetailPager(
    productDetails: ProductDetails
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {

        val pagerState = rememberPagerState()

        Box(
            modifier = Modifier
                .fillMaxSize()
        ){

            HorizontalPager(
                count = productDetails.imageSlider.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
            ) {index ->

                val imageUrl = productDetails.imageSlider[index]
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ){

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = imageUrl,
                            error = painterResource(id = R.drawable.loading_placeholder)
                        ),
                        contentDescription = "",
                        modifier = Modifier
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

    }

}