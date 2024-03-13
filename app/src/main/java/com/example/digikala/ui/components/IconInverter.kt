package com.example.digikala.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.digikala.utils.Constants.PERSIAN_LANG
import com.example.digikala.utils.Constants.USER_LANGUAGE

@Composable
fun IconInverter(
    painter: Painter,
    tint: Color
){

    if (USER_LANGUAGE == PERSIAN_LANG){
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .size(40.dp)
        )
    }else{
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .graphicsLayer(
                    rotationZ = 180f
                )
                .size(40.dp)
        )
    }

}