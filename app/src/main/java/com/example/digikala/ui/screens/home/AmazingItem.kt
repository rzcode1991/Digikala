package com.example.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.home.SpecialOfferItem
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaDarkRed
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.ui.theme.digikalaLightRedText
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.applyDiscount
import com.example.digikala.utils.DigitHelper.engToFa
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma

@Composable
fun AmazingItem(item: SpecialOfferItem){

    Card(
        modifier = Modifier
            .width(170.dp)
            .padding(
                vertical = MaterialTheme.spacing.semiLarge,
                horizontal = MaterialTheme.spacing.semiSmall
            ),
        shape = MaterialTheme.roundedShape.small,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.small
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.spacing.semiSmall
                    )
            ) {

                Text(
                    text = stringResource(id = R.string.amazing_for_app),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.digikalaLightRedText,
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.small
                        )
                )

                Spacer(modifier = Modifier.height(10.dp))

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
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.spacing.small
                    )
            ) {

                Text(
                    text = item.name,
                    color = MaterialTheme.colorScheme.darkText,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(48.dp)
                        .padding(
                            horizontal = MaterialTheme.spacing.small
                        )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.in_stock),
                        contentDescription = "",
                        modifier = Modifier
                            .size(22.dp)
                            .padding(2.dp),
                        tint = MaterialTheme.colorScheme.darkCyan
                    )

                    Text(
                        text = item.seller,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.semiDarkText
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.small
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(24.dp)
                            .background(
                                color = MaterialTheme.colorScheme.digikalaDarkRed,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ){

                        val discountPersian = engToFaAndSeparateByComma(item.discountPercent.toString())
                        Text(
                            text = "$discountPersian%",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                    }

                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            val textAfterDiscount = applyDiscount(
                                price = item.price.toLong(),
                                discountPercent = item.discountPercent
                            )
                            val commaSeparatedAndPersianPrice = engToFaAndSeparateByComma(
                                price = textAfterDiscount.toString()
                            )
                            Text(
                                text = commaSeparatedAndPersianPrice,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.toman),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(MaterialTheme.spacing.semiLarge)
                                    .padding(
                                        horizontal = MaterialTheme.spacing.extraSmall
                                    )
                            )

                        }

                        val realPricePersianCommaSeparated = engToFaAndSeparateByComma(
                            price = item.price.toString()
                        )
                        Text(
                            text = realPricePersianCommaSeparated,
                            color = Color.LightGray,
                            style = MaterialTheme.typography.bodySmall,
                            textDecoration = TextDecoration.LineThrough
                        )

                    }

                }

            }


        }

    }

}