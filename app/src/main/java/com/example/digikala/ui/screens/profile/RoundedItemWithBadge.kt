package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.LocalSpacing
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper

@Composable
fun RoundedItemWithBadge(
    title: String,
    image: Painter,
    onClick: () -> Unit,
    backGround: Color = Color.Transparent,
    fontWeight: FontWeight = FontWeight.Medium,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    hasNews: Boolean,
    notifCount: Int
){

    Column(
        modifier = Modifier
            .width(80.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .width(52.dp)
                .height(52.dp)
                .background(backGround)
        ) {

            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .size(52.dp)
            )

            if (hasNews){
                Box(
                    modifier = Modifier
                        .width(52.dp)
                        .height(52.dp),
                    contentAlignment = Alignment.BottomEnd
                ){

                    Card(
                        shape = MaterialTheme.roundedShape.extraSmall,
                        border = BorderStroke(1.dp, Color.White)
                    ){

                        Text(
                            text = DigitHelper.engToFa(notifCount.toString()),
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

        Spacer(modifier = Modifier.height(LocalSpacing.current.small))

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.darkText,
            style = style,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }

}