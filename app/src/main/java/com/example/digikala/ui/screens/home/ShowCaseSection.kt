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
                image = painterResource(id = R.drawable.digijet),
                onClick = onItemClick(
                    navController = navController,
                    url = DIGI_JET_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.digi_style),
                image = painterResource(id = R.drawable.auction),
                onClick = onItemClick(
                    navController = navController,
                    url = DIGI_STYLE_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.digi_pay),
                image = painterResource(id = R.drawable.digipay),
                onClick = onItemClick(
                    navController = navController,
                    url = DIGI_PAY_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.pindo),
                image = painterResource(id = R.drawable.pindo),
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
                image = painterResource(id = R.drawable.shopping),
                onClick = onItemClick(
                    navController = navController,
                    url = SHOPPING_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.gift_card),
                image = painterResource(id = R.drawable.giftcard),
                onClick = onItemClick(
                    navController = navController,
                    url = GIFT_CARD_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.digi_plus),
                image = painterResource(id = R.drawable.digiplus),
                onClick = onItemClick(
                    navController = navController,
                    url = DIGI_PLUS_URL
                )
            )

            RoundedItem(
                title = stringResource(id = R.string.more),
                image = painterResource(id = R.drawable.more),
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