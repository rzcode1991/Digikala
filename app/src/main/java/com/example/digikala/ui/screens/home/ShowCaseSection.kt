package com.example.digikala.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.RoundedItem
import com.example.digikala.ui.theme.LocalSpacing
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.utils.Constants.DIGI_JET_URL
import com.example.digikala.utils.Constants.DIGI_PAY_URL
import com.example.digikala.utils.Constants.DIGI_PLUS_URL
import com.example.digikala.utils.Constants.DIGI_STYLE_URL
import com.example.digikala.utils.Constants.GIFT_CARD_URL
import com.example.digikala.utils.Constants.MORE_URL
import com.example.digikala.utils.Constants.PINADO_URL
import com.example.digikala.utils.Constants.SHOPPING_URL

@Composable
fun ShowCaseSection(
    navController: NavHostController
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalSpacing.current.semiMedium,
                vertical = LocalSpacing.current.small2
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RoundedItem(
                title = stringResource(id = R.string.digikala_jet),
                image = rememberAsyncImagePainter(R.drawable.digijet),
                onClick = onItemClick(
                    navController = navController,
                    url = DIGI_JET_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.digi_style),
                image = rememberAsyncImagePainter(R.drawable.auction),
                onClick = onItemClick(
                    navController = navController,
                    url = DIGI_STYLE_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.digi_pay),
                image = rememberAsyncImagePainter(R.drawable.digipay),
                onClick = onItemClick(
                    navController = navController,
                    url = DIGI_PAY_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.pindo),
                image = rememberAsyncImagePainter(R.drawable.pindo),
                backGround = MaterialTheme.colorScheme.amber,
                onClick = onItemClick(
                    navController = navController,
                    url = PINADO_URL
                )
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RoundedItem(
                title = stringResource(id = R.string.digi_shopping),
                image = rememberAsyncImagePainter(R.drawable.shopping),
                onClick = onItemClick(
                    navController = navController,
                    url = SHOPPING_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.gift_card),
                image = rememberAsyncImagePainter(R.drawable.giftcard),
                onClick = onItemClick(
                    navController = navController,
                    url = GIFT_CARD_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.digi_plus),
                image = rememberAsyncImagePainter(R.drawable.digiplus),
                onClick = onItemClick(
                    navController = navController,
                    url = DIGI_PLUS_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.more),
                image = rememberAsyncImagePainter(R.drawable.more),
                backGround = MaterialTheme.colorScheme.grayCategory,
                onClick = onItemClick(
                    navController = navController,
                    url = MORE_URL
                )
            )

        }

    }

}


@Composable
fun onItemClick(
    navController: NavHostController,
    url: String
): () -> Unit =
    { navController.navigate(Screen.WebView.route+"?url=${url}") }