package com.example.digikala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFed1b34)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val ColorScheme.splashBg: Color
    @Composable
    get() = Color(0xFFed1b34)

val ColorScheme.selectedBottomBar: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFFCFD4DA) else Color(0xFF43474C)


val ColorScheme.unSelectedBottomBar: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFF575A5E) else Color(0xFFA4A1A1)

val ColorScheme.searchBarBg: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFF303235) else Color(0xFFF1F0EE)

val ColorScheme.darkText: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFFD8D8D8) else Color(0xFF414244)

val ColorScheme.amber: Color
    @Composable
    get() = Color(0xffFFBF00)

val ColorScheme.grayCategory: Color
    @Composable
    get() = Color(0xFFF1F0EE)

val ColorScheme.digikalaLightRed: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFF7A1414) else Color(0xffef4056)

val ColorScheme.digikalaLightRedText: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFFF8F8F8) else Color(0xffef4056)

val ColorScheme.digikalaDarkRed: Color
    @Composable
    get() = Color(0xffe6123d)

val ColorScheme.digikalaRed: Color
    @Composable
    get() = Color(0xFFed1b34)

val ColorScheme.semiDarkText: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFFD8D8D8) else Color(0xFF5C5E61)

val ColorScheme.darkCyan: Color
    @Composable
    get() = Color(0xFF0fabc6)

val ColorScheme.lightCyan: Color
    @Composable
    get() = Color(0xFF17BFD3)

val ColorScheme.digikalaLightGreen: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFF0C6810) else Color(0xff86bf3c)

val ColorScheme.bottomBarColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFF303235) else Color(0xFFFFFFFF)