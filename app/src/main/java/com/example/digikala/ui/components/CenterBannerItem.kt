package com.example.digikala.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.DIGI_CLUB

@Composable
fun CenterBannerItem(
    navController: NavHostController,
    imageUrl: String = "",
    imageId: Int? = null,
    height: Int = 170
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .padding(MaterialTheme.spacing.medium)
            .clickable {
                   navController.navigate(Screen.WebView.route + "?url=${DIGI_CLUB}")
            },
        shape = MaterialTheme.roundedShape.semiMedium
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl.ifEmpty { imageId },
                error = painterResource(id = R.drawable.loading_placeholder)
            ),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

}