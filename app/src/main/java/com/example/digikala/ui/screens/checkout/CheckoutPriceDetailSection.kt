package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartPriceDetail
import com.example.digikala.ui.components.RowWithTextAndPrice
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.ui.theme.lightCyan
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa

@Composable
fun CheckoutPriceDetailSection(
    cartPriceDetail: CartPriceDetail
){

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
                text = stringResource(id = R.string.price_details),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.darkText
            )

            Text(
                text = "${engToFa(cartPriceDetail.totalCount.toString())} ${
                    stringResource(
                        id = R.string.item
                    )
                } ",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.lightCyan
            )

        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        RowWithTextAndPrice(
            title = stringResource(id = R.string.items_price),
            price = cartPriceDetail.totalPrice.toString(),
            color = MaterialTheme.colorScheme.darkText,
            discountPercent = 0
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        RowWithTextAndPrice(
            title = stringResource(id = R.string.items_discount),
            price = cartPriceDetail.totalDiscountPrice.toString(),
            color = MaterialTheme.colorScheme.digikalaLightRed,
            discountPercent = cartPriceDetail.totalDiscountPercent
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        RowWithTextAndPrice(
            title = stringResource(id = R.string.items_total_price),
            price = cartPriceDetail.totalFinalPrice.toString(),
            color = MaterialTheme.colorScheme.darkText,
            discountPercent = 0
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.small,
                    horizontal = MaterialTheme.spacing.semiMedium
                )
                .height(1.dp)
                .alpha(0.6f),
            color = Color.LightGray
        )

    }

}