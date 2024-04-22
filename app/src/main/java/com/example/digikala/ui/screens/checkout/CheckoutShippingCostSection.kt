package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.components.RowWithTextAndPrice
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing

@Composable
fun CheckoutShippingCostSection(
    shippingCost: String,
    totalItemsPrice: String
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.semiMedium)
    ){

        RowWithTextAndPrice(
            title = stringResource(id = R.string.delivery_cost),
            price = shippingCost,
            color = MaterialTheme.colorScheme.darkText,
            discountPercent = 0
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        RowWithTextAndPrice(
            title = stringResource(id = R.string.price_to_pay),
            price = "${shippingCost.toLong() + totalItemsPrice.toLong()}",
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