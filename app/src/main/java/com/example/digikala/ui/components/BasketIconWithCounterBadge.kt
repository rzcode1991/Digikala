package com.example.digikala.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper
import com.example.digikala.viewModel.BasketViewModel

@Composable
fun BasketIconWithCounterBadge(
    basketViewModel: BasketViewModel = hiltViewModel()
){

    val totalCountForCurrentCartItems by basketViewModel.totalCountForCurrentCartItems.collectAsState(0)

    Box(
        modifier = Modifier
            .height(28.dp)
    ){

        Box(
            modifier = Modifier
                .height(28.dp)
                .width(36.dp),
            contentAlignment = Alignment.TopCenter
        ){

            Icon(
                modifier = Modifier.height(22.dp),
                painter = rememberAsyncImagePainter(model = R.drawable.cart_outline),
                contentDescription = ""
            )

        }

        if (totalCountForCurrentCartItems > 0){
            Box(
                modifier = Modifier
                    .height(28.dp),
                contentAlignment = Alignment.BottomStart
            ){

                Card(
                    shape = MaterialTheme.roundedShape.extraSmall,
                    border = BorderStroke(1.dp, Color.White)
                ){

                    Text(
                        text = DigitHelper.engToFa(totalCountForCurrentCartItems.toString()),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = MaterialTheme.roundedShape.extraSmall
                            )
                            .background(MaterialTheme.colorScheme.digikalaRed)
                            .height(16.dp)
                            .padding(
                                horizontal = MaterialTheme.spacing.semiSmall
                            ),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )

                }

            }
        }

    }

}