package com.example.digikala.ui.screens.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.cursorColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkGreen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.CommentViewModel

@Composable
fun NewCommentPositivePointsSection(
    viewModel: CommentViewModel
){

    val positivePointTxt = viewModel.positivePointTxt
    val positivePointsList = viewModel.positivePointsList

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.extraSmall
            )
    ) {

        Text(
            text = stringResource(id = R.string.positive_points),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.semiDarkText,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.extraSmall,
                    bottom = MaterialTheme.spacing.extraSmall
                )
        )

        TextField(
            value = positivePointTxt,
            onValueChange = { newValue ->
                viewModel.onPositivePointChanged(newValue)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.headlineMedium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.searchBarBg,
                unfocusedContainerColor = MaterialTheme.colorScheme.searchBarBg,
                focusedIndicatorColor = MaterialTheme.colorScheme.darkCyan,
                unfocusedIndicatorColor =  MaterialTheme.colorScheme.searchBarBg,
                cursorColor =  MaterialTheme.colorScheme.cursorColor,
                focusedTrailingIconColor = Color.Black,
                unfocusedTrailingIconColor = Color.Gray
            ),
            shape = MaterialTheme.roundedShape.small,
            trailingIcon = {
                IconButton(
                    onClick = {
                    viewModel.addPositivePoint(positivePointTxt.text)
                    viewModel.onPositivePointChanged(TextFieldValue(""))
                    }
                ) {

                    Icon(
                        Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier
                            .size(22.dp)
                    )

                }
            }
        )

        positivePointsList.forEach { pointTxt ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            top = MaterialTheme.spacing.semiSmall
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier
                            .size(18.dp),
                        tint = MaterialTheme.colorScheme.darkGreen
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small2))

                    Text(
                        text = pointTxt,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.darkText
                    )

                }

                IconButton(
                    onClick = {
                        viewModel.removePositivePoint(pointTxt)
                    }
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

            }
        }

    }

}