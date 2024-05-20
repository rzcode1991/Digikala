package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa

@Composable
fun ProductDetailsAddCommentSection(
    onClick: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.semiMedium)
            .clickable {
                onClick()
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = rememberAsyncImagePainter(model = R.drawable.digi_comments_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                Text(
                    text = stringResource(id = R.string.add_your_comment_about_this_item),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.SemiBold
                )

            }

            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.LightGray,
                modifier = Modifier
                    .size(30.dp)
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.spacing.small2,
                    bottom = MaterialTheme.spacing.semiMedium,
                    end = MaterialTheme.spacing.small2
                )
        ) {

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.biggerLarge))

            Text(
                text = engToFa(stringResource(id = R.string.add_comment_get_score_txt)),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.biggerLarge))

            Spacer(
                modifier = Modifier
                    .padding(
                        end = MaterialTheme.spacing.medium
                    )
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray.copy(alpha = 0.6f))
            )

        }

    }

}