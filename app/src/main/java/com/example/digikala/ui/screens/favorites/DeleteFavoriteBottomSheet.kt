package com.example.digikala.ui.screens.favorites

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing

@Composable
fun DeleteFavoriteBottomSheet(
    onDeleteClick: () -> Unit,
    onCancelClick: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {

        Text(
            text = stringResource(id = R.string.sure_to_delete),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = { onDeleteClick() },
                shape = MaterialTheme.roundedShape.small,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.bottomBarColor,
                    contentColor = MaterialTheme.colorScheme.digikalaRed
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.digikalaRed),
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.extraLarge
                )
            ) {

                Text(
                    text = stringResource(id = R.string.delete_item),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.digikalaRed,
                    fontWeight = FontWeight.SemiBold
                )

            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiMedium))

            Button(
                onClick = { onCancelClick() },
                shape = MaterialTheme.roundedShape.small,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.bottomBarColor,
                    contentColor = MaterialTheme.colorScheme.digikalaRed
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.digikalaRed),
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.extraLarge
                )
            ) {

                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.digikalaRed,
                    fontWeight = FontWeight.SemiBold
                )

            }

        }

    }

}