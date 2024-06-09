package com.example.digikala.ui.screens.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.favorites.FavoriteItem
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.DiscountIcon
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.golden
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper
import com.example.digikala.viewModel.FavoritesViewModel

@Composable
fun FavoriteItemView(
    favoriteItem: FavoriteItem,
    navController: NavHostController,
    viewModel: FavoritesViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.ProductDetail.withArgs(favoriteItem.itemId))
                }
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    model = favoriteItem.image,
                    error = painterResource(id = R.drawable.loading_placeholder)
                ),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(150.dp)
                    .weight(0.3f)
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Column(
                modifier = Modifier
                    .weight(0.6f)
            ) {

                Text(
                    text = favoriteItem.name,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    val mStar = String.format("%.1f", favoriteItem.star)

                    Text(
                        text = DigitHelper.engToFa(mStar),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.darkText,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.golden,
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.CenterVertically)
                    )

                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    if (favoriteItem.discountPercent > 0){
                        DiscountIcon(favoriteItem.discountPercent)
                    }

                    val priceAfterDiscount = DigitHelper.applyDiscount(
                        price = favoriteItem.price.toLong(),
                        discountPercent = favoriteItem.discountPercent
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = DigitHelper.engToFaAndSeparateByComma((priceAfterDiscount).toString()),
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

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = MaterialTheme.spacing.semiLarge
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = DigitHelper.engToFaAndSeparateByComma(favoriteItem.price.toString()),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray.copy(alpha = 0.6f),
                        textDecoration = TextDecoration.LineThrough
                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .clickable {
                        viewModel.deleteFromFavorites(favoriteItem)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Default.Delete,
                    contentDescription ="",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(16.dp)
                )

                Text(
                    text = stringResource(id = R.string.delete_from_list),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )

            }

            Row(
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.ProductDetail.withArgs(favoriteItem.itemId))
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.see_item_and_buy),
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