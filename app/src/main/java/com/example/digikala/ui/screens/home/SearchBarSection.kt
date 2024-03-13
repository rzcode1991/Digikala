package com.example.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.LocalShape
import com.example.digikala.ui.theme.LocalSpacing
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.unSelectedBottomBar
import com.example.digikala.utils.Constants.PERSIAN_LANG
import com.example.digikala.utils.Constants.USER_LANGUAGE

@Composable
fun SearchBarSection(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()){
                Color.Black
            }else{
                Color.White
            }
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.small)
                .clip(LocalShape.current.biggerSmall)
                .background(MaterialTheme.colorScheme.searchBarBg)
        ) {

            SearchBarElements()

        }

    }

}


@Composable
private fun SearchBarElements(){

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        
        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "",
            modifier = Modifier
                .height(24.dp)
                .padding(end = 20.dp)
        )

        Text(
            text = stringResource(id = R.string.search_in),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.unSelectedBottomBar,
            modifier = Modifier
                .padding(end = 5.dp)
        )
        
        Image(
            painter = DigiKalaLogoByLang(),
            contentDescription = "",
            modifier = Modifier.width(80.dp)
        )
        
    }

}


@Composable
private fun DigiKalaLogoByLang(): Painter{
    return if (USER_LANGUAGE == PERSIAN_LANG){
        painterResource(id = R.drawable.digi_red_persian)
    }else{
        painterResource(id = R.drawable.digi_red_english)
    }
}