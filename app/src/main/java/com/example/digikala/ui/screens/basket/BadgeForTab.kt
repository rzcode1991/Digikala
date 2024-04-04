package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa

@Composable
fun BadgeForTab(
    selectedIndex: Int,
    index: Int,
    count: Int
){

    val color = if (selectedIndex == index){
        MaterialTheme.colorScheme.digikalaRed
    }else{
        Color.Gray
    }

    Card(
        modifier = Modifier
            .background(Color.Transparent),
        shape = MaterialTheme.roundedShape.extraSmall
    ) {

        Text(
            text = engToFa(count.toString()),
            color = Color.White,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .background(color)
                .padding(
                    horizontal = MaterialTheme.spacing.semiSmall,
                    vertical = MaterialTheme.spacing.extraSmall
                )
        )

    }

}