package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper

@Composable
fun TopRowInBasketScreen(
    title: String,
    counter: Int
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.biggerMedium,
                bottom = MaterialTheme.spacing.semiMedium,
                start = MaterialTheme.spacing.biggerMedium,
                end = MaterialTheme.spacing.biggerMedium
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.darkText
                )

                Text(
                    text = "${DigitHelper.engToFa(counter.toString())} ${stringResource(id = R.string.item)} ",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Gray
                )

            }

            Icon(
                Icons.Filled.MoreVert,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.darkText
            )

        }

    }

}