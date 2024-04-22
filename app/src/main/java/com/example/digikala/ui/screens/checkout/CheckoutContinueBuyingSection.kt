package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma

@Composable
fun CheckoutContinueBuyingSection(
    isTimeSelected: Boolean,
    finalPrice: Long,
    navController: NavHostController,
    onClick: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f),
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
                    onClick()
                },
                shape = MaterialTheme.roundedShape.small,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isTimeSelected){
                        MaterialTheme.colorScheme.digikalaRed
                    }else{
                        MaterialTheme.colorScheme.bottomBarColor
                    },
                    contentColor = if (isTimeSelected){
                        MaterialTheme.colorScheme.bottomBarColor
                    }else{
                        MaterialTheme.colorScheme.digikalaRed
                    }
                ),
                border = if (isTimeSelected){
                    BorderStroke(0.dp, Color.Transparent)
                }else{
                    BorderStroke(2.dp, MaterialTheme.colorScheme.digikalaRed)
                },
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            ) {

                Text(
                    text = if (isTimeSelected){
                        stringResource(id = R.string.continue_buy)
                    }else{
                        stringResource(id = R.string.select_date_to_send)
                    },
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
                    .padding(MaterialTheme.spacing.small),
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = stringResource(id = R.string.price_to_pay),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {

                    Text(
                        text = engToFaAndSeparateByComma(finalPrice.toString()),
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
            color = Color.LightGray
        )

    }

}