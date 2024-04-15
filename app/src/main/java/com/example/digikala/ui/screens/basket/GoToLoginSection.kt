package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.settingArrow
import com.example.digikala.ui.theme.spacing

@Composable
fun GoToLoginSection(
    navController: NavHostController
){

    Card(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .clickable {
                navController.navigate(Screen.Profile.route)
            },
        shape = MaterialTheme.roundedShape.semiSmall,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.bottomBarColor
        ),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.6f))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.semiMedium,
                    vertical = MaterialTheme.spacing.medium
                ),
            verticalAlignment = Alignment.Top
        ) {

            Column(
                modifier = Modifier
                    .weight(0.1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = rememberAsyncImagePainter(model = R.drawable.go_to_login),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                )

            }

            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(
                        start = MaterialTheme.spacing.small2,
                        end = MaterialTheme.spacing.medium,
                        top = MaterialTheme.spacing.semiSmall,
                        bottom = MaterialTheme.spacing.semiSmall
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(id = R.string.login_or_register),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiSmall))

                Text(
                    text = stringResource(id = R.string.login_or_register_txt),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2
                )

            }

            Column(
                modifier = Modifier
                    .weight(0.1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp),
                    tint = MaterialTheme.colorScheme.settingArrow
                )

            }

        }

    }

}