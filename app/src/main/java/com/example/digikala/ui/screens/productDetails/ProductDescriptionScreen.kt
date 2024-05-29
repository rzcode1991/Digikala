package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing

@Composable
fun ProductDescriptionScreen(
    productName: String?,
    productDescription: String?,
    navController: NavHostController
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.bottomBarColor)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = MaterialTheme.spacing.small
                ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.bottomBarColor
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            shape = RectangleShape
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.small2),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier
                            .size(25.dp)
                    )
                }

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                Text(
                    text = stringResource(id = R.string.review),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Bold
                )

            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiMedium))

            Column(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.medium
                    )
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.describe),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.digikalaRed,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Spacer(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                        .height(2.5.dp)
                        .width(100.dp)
                        .background(MaterialTheme.colorScheme.digikalaRed)
                )

            }

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    horizontal = MaterialTheme.spacing.medium
                )
        ){

            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

                Text(
                    text = stringResource(id = R.string.brief_description),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))
            }

            item {
                Text(
                    text = productName ?: "",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))
            }

            item {
                if (!productDescription.isNullOrEmpty()){
                    Text(
                        text = productDescription,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.semiDarkText,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                }else{
                    Text(
                        text = stringResource(id = R.string.no_description_yet),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.semiDarkText,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                }
            }

        }

    }

}