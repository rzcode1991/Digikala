package com.example.digikala.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.spacing

@Composable
fun SearchItemView(
    product: StoreProduct,
    navController: NavHostController
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
            .clickable {
                navController.navigate(Screen.ProductDetail.withArgs(product._id))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Default.Search,
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier
                .size(20.dp)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiMedium))

        Image(
            painter = rememberAsyncImagePainter(
                model = product.image,
                error = painterResource(id = R.drawable.loading_placeholder)
            ),
            contentDescription = "",
            modifier = Modifier
                .size(20.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiMedium))

        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Medium
        )

    }

}