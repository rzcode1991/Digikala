package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartPriceDetail
import com.example.digikala.data.model.checkout.DayAndDate
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaLightGreen
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma

@Composable
fun CheckoutSendingTimeSection(
    shippingCost: String,
    allCurrentCartItems: List<CartItem>,
    currentCartPriceDetail: CartPriceDetail,
    selectedDay: DayAndDate?,
    navController: NavHostController,
    onSelectTimeTxtClick: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.medium
            )
    ) {

        Text(
            text = stringResource(id = R.string.time_and_how_to_send),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small2
                )
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiMedium))

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            shape = MaterialTheme.roundedShape.small,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.bottomBarColor
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = MaterialTheme.spacing.semiLarge
                    )
            ) {

                Text(
                    text = "${stringResource(id = R.string.consignment)} ${engToFa("1")} " +
                            "${stringResource(id = R.string.from)} ${engToFa("1")}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.semiMedium
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.semiMedium
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(model = R.drawable.k2),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.fast_send),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.digikalaLightGreen,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.spacing.small
                            )
                    )

                    Icon(
                        painter = rememberAsyncImagePainter(model = R.drawable.circle),
                        contentDescription = "",
                        modifier = Modifier
                            .size(5.dp),
                        tint = Color.LightGray
                    )

                    Text(
                        text = "${engToFa("${currentCartPriceDetail.totalCount}")} ${stringResource(id = R.string.item)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.semiDarkText,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(
                                start = MaterialTheme.spacing.small
                            )
                    )

                }

                LazyRow{

                    itemsIndexed(allCurrentCartItems){index, cartItem ->
                        val isLastItem = allCurrentCartItems.size - 1 == index
                        key(cartItem.itemId){
                            FinalSelectedItemView(
                                item = cartItem,
                                isLastItem = isLastItem,
                                onItemClick = {
                                    navController.navigate(Screen.ProductDetail.withArgs(cartItem.itemId))
                                }
                            )
                        }
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.spacing.semiMedium,
                            bottom = MaterialTheme.spacing.small
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(id = R.string.time_to_send),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )

                    if (selectedDay != null){
                        Text(
                            text = "${engToFa("9")} ${stringResource(id = R.string.to)} " +
                                    "${engToFa("22")} - ${engToFa(selectedDay.date.toString())} " +
                                    "${selectedDay.month} ",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.darkText,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(
                                    start = MaterialTheme.spacing.semiSmall
                                )
                        )
                    }else{
                        Text(
                            text = stringResource(id = R.string.not_selected),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.darkText,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(
                                    start = MaterialTheme.spacing.semiSmall
                                )
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.spacing.semiMedium
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(id = R.string.delivery_cost),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(
                                end = MaterialTheme.spacing.semiSmall
                            )
                    )

                    Text(
                        text = engToFaAndSeparateByComma(shippingCost),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.darkText,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(
                                end = MaterialTheme.spacing.extraSmall
                            )
                    )

                    Icon(
                        painter = rememberAsyncImagePainter(model = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp)
                    )

                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.spacing.semiMedium
                        )
                        .clickable {
                            onSelectTimeTxtClick()
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (selectedDay != null){
                        Text(
                            text = stringResource(id = R.string.change_date_to_send),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.darkCyan,
                            fontWeight = FontWeight.Light
                        )
                    }else{
                        Text(
                            text = stringResource(id = R.string.select_date_to_send),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.darkCyan,
                            fontWeight = FontWeight.Light
                        )
                    }

                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.darkCyan
                    )

                }

            }

        }

    }

}