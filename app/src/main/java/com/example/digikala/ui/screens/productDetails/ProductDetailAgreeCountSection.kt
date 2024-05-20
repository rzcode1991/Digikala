package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.digikalaLightGreen
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa

@Composable
fun ProductDetailAgreeCountSection(
    agreeCount: Int,
    agreePercent: Int
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Outlined.ThumbUp,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.digikalaLightGreen,
            modifier = Modifier
                .size(16.dp)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiSmall))

        Text(
            text = "${engToFa(agreePercent.toString())}% (${engToFa(agreeCount.toString())} ${stringResource(id = R.string.person)}) ${stringResource(
                id = R.string.buyers_have_suggested
            )}",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )

    }

}