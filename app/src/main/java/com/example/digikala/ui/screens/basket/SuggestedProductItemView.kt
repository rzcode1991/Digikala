package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaDarkRed
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper

@Composable
fun SuggestedProductItemView(
    item: StoreProduct,
    onAddClick: (StoreProduct) -> Unit,
    onItemClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(MaterialTheme.spacing.extraSmall),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.semiSmall)
        ) {

            Box {

                Image(
                    painter = rememberAsyncImagePainter(
                        model = item.image,
                        error = painterResource(id = R.drawable.loading_placeholder)
                    ),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clickable {
                            onItemClick()
                        }
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentAlignment = Alignment.BottomStart
                ) {

                    Surface(
                        color = MaterialTheme.colorScheme.bottomBarColor,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.small)
                            .size(26.dp)
                            .clip(CircleShape)
                            .border(1.dp, MaterialTheme.colorScheme.digikalaRed, CircleShape)
                            .clickable {
                                onAddClick(item)
                            }
                    ) {

                        Icon(
                            Icons.Default.Add,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.digikalaRed
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(10.dp))

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
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.spacing.extraSmall
                    ),
                horizontalArrangement = Arrangement.Start,
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
                ) {

                    val discountPersian =
                        DigitHelper.engToFaAndSeparateByComma(item.discountPercent.toString())
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

                        val textAfterDiscount = DigitHelper.applyDiscount(
                            price = item.price.toLong(),
                            discountPercent = item.discountPercent
                        )
                        val commaSeparatedAndPersianPrice = DigitHelper.engToFaAndSeparateByComma(
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

                    val realPricePersianCommaSeparated = DigitHelper.engToFaAndSeparateByComma(
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