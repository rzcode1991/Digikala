package com.example.digikala.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.semiDarkText

@Composable
fun NetworkErrorLoading(
    height: Dp,
    onclick: () -> Unit
){
    Column(
        modifier = Modifier
            .height(height),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyLoading(
            height = 30.dp,
            isDark = true
        )

        IconButton(onClick = { onclick() }) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp),
                tint = MaterialTheme.colorScheme.semiDarkText
            )
        }

    }
}