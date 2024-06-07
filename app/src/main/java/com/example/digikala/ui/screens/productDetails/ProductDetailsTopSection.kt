package com.example.digikala.ui.screens.productDetails

import android.content.Context
import android.content.Intent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.productDetails.Price
import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.BasketIconWithCounterBadge
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.MY_WEBSITE
import com.google.gson.Gson

@Composable
fun ProductDetailsTopSection(
    navController: NavHostController,
    productDetails: ProductDetails?
) {

    val context = LocalContext.current
    var priceList by remember {
        mutableStateOf<List<Price>?>(null)
    }
    if (productDetails != null){
        priceList = productDetails.priceList
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(onClick = {
                    navController.navigate(Screen.Basket.route)
                }) {
                    BasketIconWithCounterBadge()
                }

                var isFavorite by remember {
                    mutableStateOf(false)
                }
                val transition = updateTransition(targetState = isFavorite, label = "favorite_icon")
                val tint by transition.animateColor(label = "favorite_tint") { favorite ->
                    if (favorite) {
                        MaterialTheme.colorScheme.digikalaRed
                    } else {
                        MaterialTheme.colorScheme.darkText
                    }
                }

                IconToggleButton(
                    checked = isFavorite,
                    onCheckedChange = {
                        isFavorite = !isFavorite
                    }
                ) {

                    Icon(
                        imageVector = if (isFavorite) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = tint
                    )

                }

                var isMenuOpen by remember {
                    mutableStateOf(false)
                }

                IconButton(onClick = {
                    isMenuOpen = !isMenuOpen
                }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }

                DropdownMenu(
                    expanded = isMenuOpen,
                    onDismissRequest = {
                        isMenuOpen = false
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.bottomBarColor)
                ) {

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(id = R.string.price_chart),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.semiDarkText
                            )
                        },
                        onClick = {
                            isMenuOpen = false
                            if (priceList != null){
                                val jsonString = Gson().toJson(priceList)
                                navController.navigate(
                                    Screen.PriceChart.route + "?jsonString=${jsonString}"
                                )
                            }
                        },
                        leadingIcon = {
                            Icon(
                                painter = rememberAsyncImagePainter(R.drawable.chart),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp),
                                tint = MaterialTheme.colorScheme.semiDarkText
                            )
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(id = R.string.share_product),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.semiDarkText
                            )
                        },
                        onClick = {
                            isMenuOpen = false
                            if (productDetails != null){
                                shareToSocialMedia(
                                    context = context,
                                    productName = productDetails.name,
                                    productPrice = productDetails.price.toString()
                                )
                            }
                        },
                        leadingIcon = {
                            Icon(
                                painter = rememberAsyncImagePainter(R.drawable.share),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp),
                                tint = MaterialTheme.colorScheme.semiDarkText
                            )
                        }
                    )

                }

            }

        }

    }

}

private fun shareToSocialMedia(
    context: Context,
    productName: String,
    productPrice: String
){

    val shareIntent = Intent(Intent.ACTION_SEND)

    shareIntent.type = "text/plain"

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "$productName is available \n at price: $productPrice at: \n $MY_WEBSITE"
    )

    context.startActivity(Intent.createChooser(shareIntent, "share to..."))

}