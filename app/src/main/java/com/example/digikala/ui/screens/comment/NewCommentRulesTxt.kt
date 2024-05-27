package com.example.digikala.ui.screens.comment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.spacing

@Composable
fun NewCommentRulesTxt(
    navController: NavHostController
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.medium
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(id = R.string.submitting_comment_means),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        )

        Text(
            text = stringResource(id = R.string.digikala_publishing_rules),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.darkCyan,
            modifier = Modifier
                .padding(
                    horizontal = 2.5.dp
                )
                .clickable {
                    // TODO:
                }
        )

        Text(
            text = stringResource(id = R.string.there_is),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        )

    }

}