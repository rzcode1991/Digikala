package com.example.digikala.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper

@Composable
fun DiscountIcon(
    discountPercent: Int
){

    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.digikalaRed,
            contentColor = Color.White
        )
    ) {

        Text(
            text = "${DigitHelper.engToFa(discountPercent.toString())}%",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.semiSmall,
                    vertical = 2.dp
                )
        )

    }

}