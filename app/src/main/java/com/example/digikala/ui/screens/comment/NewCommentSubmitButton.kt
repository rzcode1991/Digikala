package com.example.digikala.ui.screens.comment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.digikala.ui.components.Loading3Dots
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing

@Composable
fun NewCommentSubmitButton(
    isButtonActive: Boolean,
    isButtonLoading: Boolean,
    onClick: () -> Unit
) {

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(
                vertical = MaterialTheme.spacing.small2
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.bottomBarColor,
            contentColor = if (isButtonActive){
                MaterialTheme.colorScheme.digikalaLightRed
            }else{
                Color.LightGray
            }
        ),
        shape = MaterialTheme.roundedShape.small,
        enabled = !isButtonLoading,
        border = BorderStroke(
            width = 1.dp,
            color = if (isButtonActive){
                MaterialTheme.colorScheme.digikalaLightRed
            }else{
                Color.LightGray.copy(alpha = 0.6f)
            }
        )
    ) {

        if (isButtonLoading){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Loading3Dots(!isSystemInDarkTheme())
            }
        }else{
            Text(
                text = stringResource(id = R.string.submit_comment),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                color = if (isButtonActive){
                    MaterialTheme.colorScheme.digikalaLightRed
                }else{
                    Color.LightGray
                }
            )
        }

    }

}