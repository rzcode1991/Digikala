package com.example.digikala.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing

@Composable
fun TextAndIconInRow(
    text: String,
    painter: Painter,
    iconTint: Color,
    textStyle: TextStyle,
    textColor: Color = MaterialTheme.colorScheme.semiDarkText
){

    Row(
        modifier = Modifier
            .padding(
                vertical = MaterialTheme.spacing.extraSmall
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painter,
            contentDescription = "",
            tint = iconTint,
            modifier = Modifier
                .size(14.dp)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Text(
            text = text,
            style = textStyle,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )

    }

}