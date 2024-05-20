package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.utils.DigitHelper

@Composable
fun BasketContinueBuyingSection(
    totalFinalPrice: Long,
    navHostController: NavHostController
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 80.dp
            )
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f),
            thickness = 1.dp,
            color = Color.LightGray
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.small,
                    vertical = MaterialTheme.spacing.extraSmall
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){

            Button(
                onClick = {
                    if (USER_TOKEN.isNotEmpty()){
                        navHostController.navigate(Screen.Checkout.route)
                    }else{
                        navHostController.navigate(Screen.Profile.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.digikalaRed,
                    contentColor = MaterialTheme.colorScheme.searchBarBg
                ),
                shape = MaterialTheme.roundedShape.small,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            ) {

                Text(
                    text = stringResource(id = R.string.continue_buy),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.small,
                            vertical = MaterialTheme.spacing.extraSmall
                        )
                )

            }

            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            ) {

                Text(
                    text = stringResource(id = R.string.items_total_price),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {

                    Text(
                        text = DigitHelper.engToFaAndSeparateByComma(totalFinalPrice.toString()),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.darkText
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp)
                    )

                }

            }

        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.6f),
            thickness = 1.dp,
            color = Color.LightGray
        )

    }
}