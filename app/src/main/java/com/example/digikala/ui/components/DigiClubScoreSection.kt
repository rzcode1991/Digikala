package com.example.digikala.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper

@Composable
fun DigiClubScoreSection(
    score: Long
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.semiMedium
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberAsyncImagePainter(R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .padding(
                        end = MaterialTheme.spacing.extraSmall
                    )
            )

            Text(
                text = stringResource(id = R.string.digiclub_score),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )

        }

        Text(
            text = "${DigitHelper.engToFa(score.toString())} ${stringResource(id = R.string.score)}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.darkText
        )

    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiMedium))

    Text(
        text = stringResource(id = R.string.digiclub_description),
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        maxLines = 2,
        modifier = Modifier
            .padding(
                vertical = MaterialTheme.spacing.semiMedium,
                horizontal = MaterialTheme.spacing.semiLarge
            )
    )

}