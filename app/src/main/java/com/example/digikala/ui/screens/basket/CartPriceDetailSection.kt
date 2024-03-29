package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartPriceDetail
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.ui.theme.lightCyan
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma

@Composable
fun CartPriceDetailSection(
    cartPriceDetail: CartPriceDetail
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.semiMedium)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.semiMedium
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.basket_summary),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.darkText
                )

                Text(
                    text = "${engToFa(cartPriceDetail.totalCount.toString())} ${stringResource(id = R.string.item)} ",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.lightCyan
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            RowWithTextAndPrice(
                title = stringResource(id = R.string.items_price),
                price = cartPriceDetail.totalPrice.toString(),
                color = MaterialTheme.colorScheme.darkText,
                discountPercent = 0
            )

            Spacer(modifier = Modifier.height(16.dp))

            RowWithTextAndPrice(
                title = stringResource(id = R.string.items_discount),
                price = cartPriceDetail.totalDiscountPrice.toString(),
                color = MaterialTheme.colorScheme.digikalaLightRed,
                discountPercent = cartPriceDetail.totalDiscountPercent
            )

            Spacer(modifier = Modifier.height(16.dp))

            RowWithTextAndPrice(
                title = stringResource(id = R.string.items_total_price),
                price = cartPriceDetail.totalFinalPrice.toString(),
                color = MaterialTheme.colorScheme.darkText,
                discountPercent = 0
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.semiMedium,
                        vertical = MaterialTheme.spacing.small
                    ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.dot_bullet),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                )

                Text(
                    text = stringResource(id = R.string.shipping_cost_alert),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.semiMedium
                        )
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            Divider(
                modifier = Modifier
                    .padding(
                        vertical = MaterialTheme.spacing.small,
                        horizontal = MaterialTheme.spacing.semiMedium
                    )
                    .fillMaxWidth()
                    .height(1.dp)
                    .alpha(0.6f),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            val digiScore = cartPriceDetail.totalFinalPrice / 100_000
            DigiClubScore(
                score = digiScore
            )

        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .alpha(0.4f),
            thickness = 12.dp,
            color = Color.LightGray
        )

    }

}


@Composable
fun RowWithTextAndPrice(
    title: String,
    price: String,
    color: Color,
    discountPercent: Long
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.semiMedium
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Row {

            Text(
                text = if (discountPercent > 0){
                    "(${engToFa(discountPercent.toString())}%) ${engToFaAndSeparateByComma(price)}"
                }else{
                    engToFaAndSeparateByComma(price)
                },
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = color
            )

            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                painter = painterResource(id = R.drawable.toman),
                tint = color,
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
            )

        }

    }
}

@Composable
fun DigiClubScore(
    score: Long
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.semiMedium
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberAsyncImagePainter(R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .padding(
                        end = MaterialTheme.spacing.extraSmall
                    )
            )

            Text(
                text = stringResource(id = R.string.digiclub_score),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )

        }

        Text(
            text = "${engToFa(score.toString())} ${stringResource(id = R.string.score)}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.darkText
        )

    }

    Spacer(modifier = Modifier.height(18.dp))

    Text(
        text = stringResource(id = R.string.digiclub_description),
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        maxLines = 2,
        modifier = Modifier
            .padding(
                vertical = MaterialTheme.spacing.semiMedium,
                horizontal = MaterialTheme.spacing.semiLarge
            )
    )

}