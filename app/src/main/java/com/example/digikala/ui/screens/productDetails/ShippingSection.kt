package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.digikala.R
import com.example.digikala.ui.components.TextAndIconInRow
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.digikalaLightGreen
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing

@Composable
fun ShippingSection(
    productSeller: String
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {

            Column(
                modifier = Modifier
                    .width(25.dp)
                    .fillMaxHeight()
                    .padding(
                        vertical = MaterialTheme.spacing.extraSmall
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.in_stock),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.darkCyan,
                    modifier = Modifier
                        .size(20.dp)
                )

                Divider(
                    modifier = Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .alpha(0.6f),
                    color = Color.LightGray
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.darkCyan,
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp)
                )

                Divider(
                    modifier = Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .alpha(0.6f),
                    thickness = 2.dp,
                    color = Color.LightGray
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.darkCyan,
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp)
                )

            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        vertical = MaterialTheme.spacing.extraSmall
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = productSeller,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                TextAndIconInRow(
                    text = stringResource(id = R.string.digikala_send),
                    painter = painterResource(id = R.drawable.k1),
                    iconTint = MaterialTheme.colorScheme.digikalaLightRed,
                    textStyle = MaterialTheme.typography.headlineSmall,
                    textColor = Color.Gray
                )

                Spacer(modifier = Modifier.height(1.dp))

                TextAndIconInRow(
                    text = stringResource(id = R.string.fast_send),
                    painter = painterResource(id = R.drawable.k2),
                    iconTint = MaterialTheme.colorScheme.digikalaLightGreen,
                    textStyle = MaterialTheme.typography.headlineSmall,
                    textColor = Color.Gray
                )

            }

        }

        Spacer(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.biggerMedium
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray.copy(alpha = 0.6f))
        )

    }

}