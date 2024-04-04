package com.example.digikala.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa

@Composable
fun IconWithLargeBadge(
    item: BottomNavItem,
    totalCountForCurrentCartItems: Int,
    isSelected: Boolean
){

    Box(
        modifier = Modifier
            .height(28.dp)
    ){

        Box(
            modifier = Modifier
                .height(28.dp)
                .width(36.dp),
            contentAlignment = Alignment.TopCenter
        ){

            IconForSelectedOrDeselected(
                isSelected = isSelected,
                item = item
            )

        }

        Box(
            modifier = Modifier
                .height(28.dp),
            contentAlignment = Alignment.BottomStart
        ){

            Card(
                shape = MaterialTheme.roundedShape.extraSmall,
                border = BorderStroke(1.dp, Color.White)
            ){

                Text(
                    text = engToFa(totalCountForCurrentCartItems.toString()),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.digikalaRed)
                        .height(16.dp)
                        .padding(
                            horizontal = MaterialTheme.spacing.semiSmall
                        ),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )

            }

        }

    }

}

@Composable
private fun IconForSelectedOrDeselected(
    isSelected: Boolean,
    item: BottomNavItem
){
    if (isSelected){
        Icon(
            modifier = Modifier.height(22.dp),
            painter = item.selectedIcon,
            contentDescription = item.name
        )
    }else{
        Icon(
            modifier = Modifier.height(22.dp),
            painter = item.deSelectedIcon,
            contentDescription = item.name
        )
    }
}