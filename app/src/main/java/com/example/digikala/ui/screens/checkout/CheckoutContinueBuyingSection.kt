package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.components.Loading3Dots
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma

@Composable
fun CheckoutContinueBuyingSection(
    isTimeSelected: Boolean,
    isButtonLoading: Boolean,
    finalPrice: Long,
    onClick: () -> Unit
){

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.bottomBarColor
        )
    ){

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

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

                    if (isButtonLoading){
                        Row(
                            modifier = Modifier
                                .width(120.dp)
                                .padding(
                                    horizontal = MaterialTheme.spacing.small,
                                    vertical = MaterialTheme.spacing.semiSmall
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Loading3Dots(isDark = isSystemInDarkTheme())
                        }
                    }else{
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

        }

    }

}