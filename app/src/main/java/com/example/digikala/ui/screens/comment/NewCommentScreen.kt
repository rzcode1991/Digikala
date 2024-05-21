package com.example.digikala.ui.screens.comment

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R

@Composable
fun NewCommentScreen(
    navController: NavHostController,
    productName: String,
    productImage: String
){

    val decodedImage = Uri.decode(productImage)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = decodedImage,
                error = painterResource(id = R.drawable.loading_placeholder)
            ),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = productName)

    }

}