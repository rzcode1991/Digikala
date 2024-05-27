package com.example.digikala.ui.screens.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.digikala.R
import com.example.digikala.ui.theme.cursorColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.CommentViewModel

@Composable
fun NewCommentTitleSection(
    viewModel: CommentViewModel
){

    val title = viewModel.title

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.extraSmall
            )
    ) {

        Text(
            text = stringResource(id = R.string.describe_your_comment),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

        Text(
            text = stringResource(id = R.string.comment_title),
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
            value = title,
            onValueChange = { newValue ->
                viewModel.onTitleChanged(newValue)
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
                cursorColor =  MaterialTheme.colorScheme.cursorColor
            ),
            shape = MaterialTheme.roundedShape.small
        )

    }

}