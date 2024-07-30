package com.example.digikala.ui.screens.profile

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.cursorColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.spacing

@Composable
fun MyEditText(
    value: String,
    onValueChane: (String) -> Unit,
    hint: String,
    enabled: Boolean,
    startPadding: Dp = MaterialTheme.spacing.semiLarge,
    endPadding: Dp = MaterialTheme.spacing.semiLarge,
    topPadding: Dp = MaterialTheme.spacing.medium,
    bottomPadding: Dp = MaterialTheme.spacing.semiLarge,
    height: Dp = 92.dp,
    isSingleLine: Boolean = true,
    maxLines: Int = 1
){

    TextField(
        value = value,
        onValueChange = { newValue ->
            onValueChane(newValue)
        },
        enabled = enabled,
        singleLine = isSingleLine,
        maxLines = maxLines,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(
                start = startPadding,
                end = endPadding,
                top = topPadding,
                bottom = bottomPadding
            ),
        textStyle = MaterialTheme.typography.headlineMedium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.searchBarBg,
            unfocusedContainerColor = MaterialTheme.colorScheme.searchBarBg,
            focusedIndicatorColor = MaterialTheme.colorScheme.darkCyan,
            unfocusedIndicatorColor =  MaterialTheme.colorScheme.searchBarBg,
            cursorColor =  MaterialTheme.colorScheme.cursorColor
        ),
        placeholder = {
            if (hint.isNotEmpty()){
                Text(
                    text = hint,
                    color = Color.Gray,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Medium
                )
            }
        },
        shape = MaterialTheme.roundedShape.small

    )

}