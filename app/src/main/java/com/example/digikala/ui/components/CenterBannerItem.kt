package com.example.digikala.ui.components

import androidx.compose.foundation.Image
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
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing

@Composable
fun CenterBannerItem(
    imageUrl: String = "",
    imageId: Int? = null,
    height: Int = 170
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .padding(MaterialTheme.spacing.medium),
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