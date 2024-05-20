package com.example.digikala.ui.screens.productDetails

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.BasketIconWithCounterBadge
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.spacing

@Composable
fun ProductDetailsTopSection(
    navController: NavHostController
){

    var isFavorite by remember {
        mutableStateOf(false)
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
        ){

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

                IconButton(onClick = {
                    isFavorite = !isFavorite
                }) {
                    Icon(
                        if (isFavorite){
                            Icons.Default.Favorite
                        }else{
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = if (isFavorite){
                            MaterialTheme.colorScheme.digikalaRed
                        }else{
                            MaterialTheme.colorScheme.darkText
                        }
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }

            }

        }

    }

}