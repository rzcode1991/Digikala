package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.settingArrow
import com.example.digikala.ui.theme.spacing

@Composable
fun RowWithIconAndTextItemView(
    lastItem: Boolean,
    imagePainterId: Int,
    titleId: Int,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.semiMedium,
                top = MaterialTheme.spacing.small2,
                bottom = if (!lastItem) MaterialTheme.spacing.small2 else 0.dp
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = MaterialTheme.spacing.small2
                )
                .clickable(
                    onClick = onClick,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = rememberAsyncImagePainter(model = imagePainterId),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                Text(
                    text = stringResource(id = titleId),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

            }

            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier
                    .width(20.dp)
                    .size(20.dp),
                tint = MaterialTheme.colorScheme.settingArrow
            )

        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small2))

        if (!lastItem) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(
                        start = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.small2
                    )
                    .alpha(0.6f),
                thickness = 1.dp,
                color = Color.LightGray
            )
        }

    }

}