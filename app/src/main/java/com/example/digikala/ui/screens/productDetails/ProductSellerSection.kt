package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.ui.theme.darkGreen
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa

@Composable
fun ProductSellerSection(
    productDetails: ProductDetails
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {

        Text(
            text = stringResource(id = R.string.seller),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = rememberAsyncImagePainter(model = R.drawable.store),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.biggerMedium))

                Column {

                    Text(
                        text = productDetails.seller,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiMedium))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "${engToFa(productDetails.agreePercent.toString())}%",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.darkGreen
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

                        Text(
                            text = stringResource(id = R.string.buyers_satisfaction),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )

                        Spacer(
                            modifier = Modifier
                                .padding(
                                    horizontal = MaterialTheme.spacing.small
                                )
                                .width(1.dp)
                                .height(MaterialTheme.spacing.medium)
                                .background(Color.LightGray.copy(alpha = 0.6f))
                        )

                        Text(
                            text = stringResource(id = R.string.function),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

                        Text(
                            text = stringResource(id = R.string.perfect),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.darkGreen
                        )

                    }

                }

            }

            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.LightGray,
                modifier = Modifier
                    .size(20.dp)
            )

        }

        Spacer(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.biggerLarge,
                    end = MaterialTheme.spacing.small2,
                    top = MaterialTheme.spacing.medium
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray.copy(alpha = 0.6f))
        )

    }

}