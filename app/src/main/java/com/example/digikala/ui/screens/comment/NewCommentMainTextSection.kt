package com.example.digikala.ui.screens.comment

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.cursorColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.CommentViewModel

@Composable
fun NewCommentMainTextSection(
    viewModel: CommentViewModel
){

    val mainText = viewModel.mainText

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.extraSmall
            )
    ) {

        Text(
            text = stringResource(id = R.string.comment_main_txt),
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
            value = mainText,
            onValueChange = { newValue ->
                viewModel.onMainTxtChanged(newValue)
            },
            maxLines = 10,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
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

        Spacer(
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.small2
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray.copy(alpha = 0.6f))
        )

    }

}