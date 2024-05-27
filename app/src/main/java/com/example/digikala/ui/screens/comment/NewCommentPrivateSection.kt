package com.example.digikala.ui.screens.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.CommentViewModel

@Composable
fun NewCommentPrivateSection(
    viewModel: CommentViewModel
){

    val switchState = viewModel.switchState

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.extraSmall,
                bottom = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(id = R.string.private_comment),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.semiDarkText
        )

        Switch(
            checked = switchState,
            onCheckedChange = {
                viewModel.onSwitchStateChanged(it)
            },
            colors = SwitchDefaults.colors(
                checkedBorderColor = MaterialTheme.colorScheme.darkCyan,
                uncheckedBorderColor = MaterialTheme.colorScheme.darkText,
                checkedThumbColor = Color.White,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = MaterialTheme.colorScheme.darkCyan,
                uncheckedTrackColor = MaterialTheme.colorScheme.bottomBarColor
            )
        )

    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.LightGray.copy(alpha = 0.6f))
    )

}