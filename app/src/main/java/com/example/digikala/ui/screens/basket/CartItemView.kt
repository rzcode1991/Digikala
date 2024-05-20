package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.ui.components.TextAndIconInRow
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaLightGreen
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.applyDiscount
import com.example.digikala.utils.DigitHelper.engToFa
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma
import com.example.digikala.viewModel.BasketViewModel

@Composable
fun CartItemView(
    item: CartItem,
    onItemClick: () -> Unit,
    viewModel: BasketViewModel = hiltViewModel()
){

    var counter by remember {
        mutableStateOf(item.count)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = MaterialTheme.spacing.extraSmall
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.bottomBarColor
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        model = item.image,
                        error = painterResource(id = R.drawable.loading_placeholder)
                        ),
                    contentDescription = "",
                    modifier = Modifier
                        .width(130.dp)
                        .height(90.dp)
                        .weight(0.3f)
                        .clickable {
                            onItemClick()
                        }
                )

                Column(
                    modifier = Modifier
                        .weight(0.7f)
                ) {

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.darkText,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(
                                vertical = MaterialTheme.spacing.extraSmall
                            )
                    )

                    TextAndIconInRow(
                        text = stringResource(id = R.string.warranty),
                        painter = painterResource(id = R.drawable.warranty),
                        iconTint = MaterialTheme.colorScheme.darkText,
                        textStyle = MaterialTheme.typography.labelSmall
                    )

                    TextAndIconInRow(
                        text = stringResource(id = R.string.digikala),
                        painter = painterResource(id = R.drawable.store),
                        iconTint = MaterialTheme.colorScheme.darkText,
                        textStyle = MaterialTheme.typography.labelSmall
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier
                                .width(16.dp)
                                .fillMaxHeight()
                                .padding(
                                    vertical = MaterialTheme.spacing.extraSmall
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.in_stock),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.darkCyan,
                                modifier = Modifier
                                    .size(14.dp)
                            )

                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(14.dp)
                                    .alpha(0.6f),
                                color = Color.LightGray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.darkCyan,
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp)
                            )

                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(14.dp)
                                    .alpha(0.6f),
                                thickness = 2.dp,
                                color = Color.LightGray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.darkCyan,
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp)
                            )

                        }

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(
                                    vertical = MaterialTheme.spacing.extraSmall
                                ),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = item.seller,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.semiDarkText,
                                modifier = Modifier
                                    .padding(
                                        bottom = MaterialTheme.spacing.extraSmall
                                    )
                            )

                            TextAndIconInRow(
                                text = stringResource(id = R.string.digikala_send),
                                painter = painterResource(id = R.drawable.k1),
                                iconTint = MaterialTheme.colorScheme.digikalaLightRed,
                                textStyle = MaterialTheme.typography.labelSmall
                            )

                            TextAndIconInRow(
                                text = stringResource(id = R.string.fast_send),
                                painter = painterResource(id = R.drawable.k2),
                                iconTint = MaterialTheme.colorScheme.digikalaLightGreen,
                                textStyle = MaterialTheme.typography.labelSmall
                            )

                        }

                    }

                }

            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            Row(
                modifier = Modifier
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.LightGray.copy(alpha = 0.6f),
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
                                vertical = MaterialTheme.spacing.extraSmall,
                                horizontal = MaterialTheme.spacing.small
                            )
                            .clickable {
                                counter++
                                viewModel.updateCartItem(item.copy(count = counter))
                            }
                    )

                    Text(
                        text = engToFa(counter.toString()),
                        color = MaterialTheme.colorScheme.digikalaRed,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(
                                vertical = MaterialTheme.spacing.extraSmall,
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
                                    vertical = MaterialTheme.spacing.extraSmall,
                                    horizontal = MaterialTheme.spacing.small
                                )
                                .clickable {
                                    viewModel.deleteCartItem(item)
                                }
                        )
                    }else{
                        Icon(
                            painter = painterResource(id = R.drawable.ic_decrease_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.digikalaRed,
                            modifier = Modifier
                                .padding(
                                    vertical = MaterialTheme.spacing.extraSmall,
                                    horizontal = MaterialTheme.spacing.small
                                )
                                .clickable {
                                    counter--
                                    viewModel.updateCartItem(item.copy(count = counter))
                                }
                        )
                    }

                }
                
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiLarge))

                Column {

                    val priceAfterDiscount = applyDiscount(item.price.toLong(), item.discountPercent)
                    val discountAmount = (item.price.toLong()) - (priceAfterDiscount)
                    Text(
                        text = "${engToFaAndSeparateByComma((discountAmount * counter).toString())} ${stringResource(
                            id = R.string.tooman_off
                        )}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.digikalaLightRed
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row {

                        Text(
                            text = engToFaAndSeparateByComma((priceAfterDiscount * counter).toString()),
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.darkText
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.toman),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(MaterialTheme.spacing.extraSmall)
                        )

                    }

                }

            }
            
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.changeCartItemStatus(
                            newStatus = CartStatus.NEXT_CART,
                            id = item.itemId
                        )
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.save_into_next_cart),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.darkCyan,
                    fontWeight = FontWeight.Light
                )

                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.darkCyan
                )

            }

        }

    }

}