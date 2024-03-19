package com.example.digikala.ui.screens.basket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.ui.theme.spacing

@Composable
fun CartItemView(
    item: CartItem
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.extraSmall)
    ) {

        Text(text = item.name)

    }

}