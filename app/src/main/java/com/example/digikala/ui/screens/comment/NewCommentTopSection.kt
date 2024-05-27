package com.example.digikala.ui.screens.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing

@Composable
fun NewCommentTopSection(
    navController: NavHostController,
    productName: String,
    decodedImage: String
){

    Card(
        modifier = Modifier
            .fillMaxWidth(),
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

            Image(
                painter = rememberAsyncImagePainter(
                    model = decodedImage,
                    error = painterResource(id = R.drawable.loading_placeholder)
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small2))

            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(id = R.string.your_comment),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.darkText
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                Text(
                    text = productName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.semiDarkText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }

        }

    }

}