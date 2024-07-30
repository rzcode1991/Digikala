package com.example.digikala.ui.screens.profile

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.checkout.DayAndDate
import com.example.digikala.data.model.checkout.Order
import com.example.digikala.data.model.checkout.OrderStatus
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma

@Composable
fun OrderItemView(
    orders: List<Order>,
    navController: NavHostController
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        items(orders) { order ->
            OrderView(
                order = order,
                navController = navController
            )
        }

    }

}

@Composable
private fun OrderView(
    order: Order,
    navController: NavHostController
) {

    val dayAndDate = DayAndDate.fromJson(order.orderDate)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small)
            .clickable {
                if (order.orderStatus == OrderStatus.WAITING_FOR_PAY) {
                    navController.navigate(
                        Screen.ConfirmPurchase.withArgs(
                            order.orderId, order.totalPrice
                        )
                    )
                }
            },
        shape = MaterialTheme.roundedShape.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.bottomBarColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .weight(0.7f)
                ) {

                    Text(
                        text = stringResource(id = R.string.order_id),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                    Text(
                        text = order.orderId,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Medium
                    )

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .weight(0.3f)
                ) {

                    Text(
                        text = engToFaAndSeparateByComma(order.totalPrice.toString()),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                    Icon(
                        painter = rememberAsyncImagePainter(model = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(15.dp)
                    )

                }

            }

            Text(
                text = if (dayAndDate != null) {
                    "${dayAndDate.day} ${dayAndDate.date} ${dayAndDate.month}"
                } else {
                    stringResource(id = R.string.invalid_day_and_date)
                },
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(
                        vertical = MaterialTheme.spacing.medium
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                LazyRow(
                    modifier = Modifier
                        .weight(1f)
                ) {

                    items(order.items) { item ->
                        orderImageWithDivider(item)
                    }

                }

                IconButton(
                    onClick = {
                        if (order.orderStatus == OrderStatus.WAITING_FOR_PAY) {
                            navController.navigate(
                                Screen.ConfirmPurchase.withArgs(
                                    order.orderId, order.totalPrice
                                )
                            )
                        }
                    }
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp),
                        tint = Color.Gray
                    )
                }

            }

        }

    }

}

@Composable
private fun orderImageWithDivider(
    item: CartItem
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = item.image,
                error = painterResource(id = R.drawable.loading_placeholder)
            ),
            contentDescription = "",
            modifier = Modifier
                .size(30.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

        Divider(
            color = Color.LightGray.copy(alpha = 0.6f),
            modifier = Modifier
                .width(1.dp)
                .height(30.dp)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

    }

}