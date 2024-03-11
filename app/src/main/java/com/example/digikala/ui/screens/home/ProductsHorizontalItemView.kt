package com.example.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraBoldNumber
import com.example.digikala.ui.theme.spacing

@Composable
fun ProductsHorizontalItemView(
    name: String,
    id: String,
    imageUrl: String
){

    Row(
        modifier = Modifier
            .width(320.dp)
            .padding(
                bottom = MaterialTheme.spacing.extraSmall
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl,
                error = painterResource(id = R.drawable.loading_placeholder)
            ),
            contentDescription = "",
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = id,
            style = MaterialTheme.typography.extraBoldNumber,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.darkCyan,
            modifier = Modifier
                .weight(0.1f)
                .padding(
                    horizontal = MaterialTheme.spacing.small
                )
        )

        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
                .padding(
                    vertical = MaterialTheme.spacing.small
                )
        ) {

            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.spacing.small
                    )
            )

        }

    }

}