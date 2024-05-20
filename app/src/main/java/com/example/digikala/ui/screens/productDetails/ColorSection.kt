package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.ui.theme.lightCyan
import com.example.digikala.ui.theme.spacing

@Composable
fun ColorSection(
    productDetails: ProductDetails
) {

    if (productDetails.colors.isNotEmpty()){

        var selectedColor by remember {
            mutableStateOf(productDetails.colors[0].color)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "${stringResource(id = R.string.color)}: $selectedColor",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            LazyRow(
                verticalAlignment = Alignment.CenterVertically
            ){

                items(productDetails.colors){color ->
                    ColorWithText(
                        colorName = color.color,
                        colorCode = color.code,
                        isSelected = selectedColor == color.color
                    ){
                        selectedColor = color.color
                    }

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiMedium))
                }

            }

        }

    }

}

@Composable
private fun ColorWithText(
    colorName: String,
    colorCode: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val color = Color(android.graphics.Color.parseColor(colorCode))

    Card(
        modifier = Modifier
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        border = if (isSelected) {
            BorderStroke(1.dp, MaterialTheme.colorScheme.lightCyan)
        } else {
            BorderStroke(0.dp, Color.Transparent)
        }
    ) {
        
        Row(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.semiSmall,
                    end = MaterialTheme.spacing.small2,
                    top = MaterialTheme.spacing.semiSmall,
                    bottom = MaterialTheme.spacing.semiSmall
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(20.dp),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = rememberAsyncImagePainter(model = R.drawable.circle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .border(
                            shape = CircleShape,
                            width = if (colorCode == "#FFFFFF") 1.dp else 0.dp,
                            color = if (colorCode == "#FFFFFF") Color.LightGray else Color.Transparent
                        ),
                    tint = color
                )

                if (isSelected) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "",
                        modifier = Modifier
                            .size(10.dp)
                    )
                }

            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiSmall))

            Text(
                text = colorName,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )

        }

    }

}