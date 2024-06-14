package com.example.digikala.ui.screens.comment

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
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.productDetails.Comment
import com.example.digikala.ui.theme.darkGreen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.orange
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.PERSIAN_LANG
import com.example.digikala.utils.Constants.USER_LANGUAGE
import com.example.digikala.utils.DigitHelper.engToFa
import com.example.digikala.viewModel.ProductDetailsViewModel

@Composable
fun CommentItemView(
    comment: Comment,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    onClick: (String) -> Unit
) {

    val color = if (comment.star > 2.5) {
        MaterialTheme.colorScheme.darkGreen
    } else {
        MaterialTheme.colorScheme.orange
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(comment.productId)
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.ThumbUp,
                contentDescription = "",
                tint = color,
                modifier = if (comment.star > 2.5) {
                    Modifier
                        .size(18.dp)
                } else {
                    Modifier
                        .graphicsLayer(scaleY = -1f)
                        .size(18.dp)
                }
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

            Text(
                text = if (comment.star > 2.5) {
                    stringResource(id = R.string.i_suggest)
                } else {
                    stringResource(id = R.string.i_dont_suggest)
                },
                style = MaterialTheme.typography.labelSmall,
                color = color,
                fontWeight = FontWeight.Medium
            )

        }

        Text(
            text = comment.title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            text = comment.description,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.semiDarkText,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = engToFa(comment.star.toString() + ".0"),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .clip(MaterialTheme.roundedShape.small)
                    .width(MaterialTheme.spacing.large)
                    .background(color),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            val creationTime = viewModel.getTimeDifferenceFromNow(comment.createdAt)

            Text(
                text = creationTime,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            Text(
                text = stringResource(id = R.string.dot_bullet),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.spacing.extraSmall
                    )
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            var userName = comment.userName
            if (userName.contains(" - ")){
                val firstName = userName.split(" - ")[0]
                val lastName = userName.split(" - ")[1]
                userName = if (USER_LANGUAGE == PERSIAN_LANG) {
                    "$firstName $lastName"
                } else {
                    "$lastName $firstName"
                }
            }

            Text(
                text = userName,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )

        }

        Spacer(
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.medium
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray.copy(alpha = 0.6f))
        )

    }

}