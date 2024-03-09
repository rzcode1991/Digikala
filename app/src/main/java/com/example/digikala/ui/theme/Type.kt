package com.example.digikala.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.digikala.R


val font_bold = FontFamily(
    Font(R.font.iranyekanbold)
)
val font_medium = FontFamily(
    Font(R.font.iranyekanmedium)
)
val font_standard = FontFamily(
    Font(R.font.iranyekan)
)

val Typography.extraBoldNumber: TextStyle
@Composable
get() = TextStyle(
    fontFamily = font_bold,
    fontWeight = FontWeight.Bold,
    fontSize = 26.sp
)

val Typography = Typography(

    // extraSmall
    labelSmall = TextStyle(
        fontFamily = font_standard,
        fontSize = 11.sp,
        lineHeight = 25.sp
    ),

    //body 1
    bodyMedium = TextStyle(
        fontFamily = font_medium,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 25.sp
    ),
    //body 2
    bodySmall = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 25.sp
    ),
    //h1
    displayLarge = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 25.sp
    ),
    //h2
    displayMedium = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 25.sp
    ),
    //h3
    displaySmall = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 25.sp
    ),
    //h4
    headlineLarge = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 25.sp
    ),
    //h5
    headlineMedium = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 25.sp
    ),
    //h6
    headlineSmall = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 25.sp
    )


)