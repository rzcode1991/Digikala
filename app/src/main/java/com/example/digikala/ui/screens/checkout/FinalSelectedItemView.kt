package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper
import com.example.digikala.viewModel.BasketViewModel

@Composable
fun FinalSelectedItemView(
    item: CartItem,
    isLastItem: Boolean,
    onItemClick: () -> Unit,
    basketViewModel: BasketViewModel = hiltViewModel()
){

    var counter by remember {
        mutableStateOf(item.count)
    }

    Column(
        modifier = Modifier
            .width(160.dp)
            .padding(
                vertical = MaterialTheme.spacing.semiLarge
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            if (!isLastItem){
                Spacer(modifier = Modifier.width(1.dp))
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        vertical = MaterialTheme.spacing.small
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        model = item.image,
                        error = painterResource(id = R.drawable.loading_placeholder)
                    ),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(
                            horizontal = MaterialTheme.spacing.extraSmall
                        )
                        .clickable {
                            onItemClick()
                        }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.LightGray.copy(alpha = 0.5f),
                            shape = MaterialTheme.roundedShape.semiSmall
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_increase_24),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.digikalaRed,
                        modifier = Modifier
                            .padding(
                                vertical = MaterialTheme.spacing.small,
                                horizontal = MaterialTheme.spacing.small
                            )
                            .clickable {
                                counter++
                                basketViewModel.updateCartItem(item.copy(count = counter))
                            }
                    )

                    Text(
                        text = DigitHelper.engToFa(counter.toString()),
                        color = MaterialTheme.colorScheme.digikalaRed,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(
                                vertical = MaterialTheme.spacing.small,
                                horizontal = MaterialTheme.spacing.small
                            )
                    )

                    if (counter == 1){
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.digikalaRed,
                            modifier = Modifier
                                .padding(
                                    vertical = MaterialTheme.spacing.small,
                                    horizontal = MaterialTheme.spacing.small
                                )
                                .clickable {
                                    basketViewModel.deleteCartItem(item)
                                }
                        )
                    }else{
                        Icon(
                            painter = painterResource(id = R.drawable.ic_decrease_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.digikalaRed,
                            modifier = Modifier
                                .padding(
                                    vertical = MaterialTheme.spacing.small,
                                    horizontal = MaterialTheme.spacing.small
                                )
                                .clickable {
                                    counter--
                                    basketViewModel.updateCartItem(item.copy(count = counter))
                                }
                        )
                    }

                }

            }

            if (!isLastItem){
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(180.dp)
                        .alpha(0.4f),
                    color = Color.LightGray
                )
            }

        }

    }

}