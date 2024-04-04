package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.example.digikala.ui.theme.font_standard
import com.example.digikala.ui.theme.spacing

@Composable
fun TermsAndRulesText(
    fullText: String,
    underlinedTexts: List<String>,
    fontSize: TextUnit,
    underlinedTextFontWeight: FontWeight = FontWeight.Medium,
    underlinedTextDecoration: TextDecoration = TextDecoration.Underline,
    textColor: Color,
    textAlign: TextAlign
){

    Text(
        text = buildAnnotatedString {
            append(fullText)
            underlinedTexts.forEach { underlineText ->
                val startPoint = fullText.indexOf(underlineText)
                val endPoint = startPoint + underlineText.length
                addStyle(
                    style = SpanStyle(
                        fontSize = fontSize,
                        fontWeight = underlinedTextFontWeight,
                        textDecoration = underlinedTextDecoration
                    ),
                    start = startPoint,
                    end = endPoint
                )

                addStyle(
                    style = SpanStyle(
                        fontSize = fontSize,
                        fontFamily = font_standard,
                        color = textColor
                    ),
                    start = 0,
                    end = fullText.length
                )
            }
        },
        modifier = Modifier
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.medium
            ),
        textAlign = textAlign
    )

}