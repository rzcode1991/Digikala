package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.golden
import com.example.digikala.ui.theme.lightCyan
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa

@Composable
fun ScoreAndCommentsSection(
    productDetails: ProductDetails
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.medium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ScoreSection(
            star = productDetails.star,
            starCount = productDetails.starCount
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Icon(
            painter = rememberAsyncImagePainter(model = R.drawable.circle),
            contentDescription = "",
            modifier = Modifier
                .size(4.dp),
            tint = Color.LightGray
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Text(
            text = "${engToFa(productDetails.commentCount.toString())} ${stringResource(id = R.string.users_comments)}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.lightCyan,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Icon(
            painter = rememberAsyncImagePainter(model = R.drawable.circle),
            contentDescription = "",
            modifier = Modifier
                .size(4.dp),
            tint = Color.LightGray
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Text(
            text = "${engToFa(productDetails.questionCount.toString())} ${stringResource(id = R.string.q_and_a)}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.lightCyan,
            fontWeight = FontWeight.Medium
        )

    }

}

@Composable
private fun ScoreSection(
    star: Double,
    starCount: Int
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            Icons.Filled.Star,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.golden,
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.CenterVertically)
        )

        val mStar = String.format("%.1f", star)

        Text(
            text = engToFa(mStar),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.extraSmall,
                    end = MaterialTheme.spacing.semiSmall
                )
        )

        Text(
            text = "(${stringResource(id = R.string.score)} ${engToFa(starCount.toString())} ${stringResource(
                id = R.string.buyer
            )})",
            style = MaterialTheme.typography.labelSmall,
            color = Color.LightGray,
            fontWeight = FontWeight.Medium
        )

    }

}