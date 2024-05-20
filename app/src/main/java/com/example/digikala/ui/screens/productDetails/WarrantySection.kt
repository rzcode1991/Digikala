package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.ui.theme.spacing

@Composable
fun WarrantySection(){

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.extraSmall
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = R.drawable.warranty),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.biggerMedium))

            Text(
                text = stringResource(id = R.string.warranty_txt),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Medium
            )

        }

        Spacer(
            modifier = Modifier
                .padding(
                    start = 50.dp,
                    end = MaterialTheme.spacing.semiLarge,
                    top = MaterialTheme.spacing.medium
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray.copy(alpha = 0.6f))
        )

    }

}