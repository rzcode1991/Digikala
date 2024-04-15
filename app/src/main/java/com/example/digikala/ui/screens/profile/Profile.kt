package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.profile.MyOrdersIcons
import com.example.digikala.data.model.profile.RowWithIconAndTextItem
import com.example.digikala.ui.components.CenterBannerItem
import com.example.digikala.ui.components.RoundedItem
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.utils.DigitHelper.engToFa

@Composable
fun Profile(){

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 80.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        item {
            ProfileTopBarSection()
        }
        item {
            ProfileUserInfoSection()
        }
        item {
            ProfileMiddleSection()
        }
        item {
            MyOrdersSection()
        }
        item {
            CenterBannerItem(
                imageId = R.drawable.digiclub1,
                height = 200
            )
        }
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiMedium))
        }

        val rowWithIconAndTextItems = listOf(
            RowWithIconAndTextItem(
                titleId = R.string.digi_plus,
                imageId = R.drawable.digi_plus_icon
            ),
            RowWithIconAndTextItem(
                titleId = R.string.fav_list,
                imageId = R.drawable.digi_fav_icon
            ),
            RowWithIconAndTextItem(
                titleId = R.string.my_comments,
                imageId = R.drawable.digi_comments_icon
            ),
            RowWithIconAndTextItem(
                titleId = R.string.addresses,
                imageId = R.drawable.digi_adresses_icon
            ),
            RowWithIconAndTextItem(
                titleId = R.string.profile_info,
                imageId = R.drawable.digi_profile_icon,
                lastItem = true
            )
        )

        items(rowWithIconAndTextItems){
            RowWithIconAndTextItemView(
                lastItem = it.lastItem,
                imagePainterId = it.imageId,
                titleId = it.titleId
            )
        }

        item {
            CenterBannerItem(
                imageId = R.drawable.digiclub2,
                height = 200
            )
        }


    }

}

@Composable
private fun ProfileTopBarSection(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.semiMedium,
                vertical = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = { /*TODO*/ }
        ) {

            Icon(
                painter = painterResource(id = R.drawable.digi_settings),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.selectedBottomBar,
                modifier = Modifier
                    .size(MaterialTheme.spacing.semiLarge)
            )

        }

        IconButton(
            onClick = { /*TODO*/ }
        ) {

            Icon(
                Icons.Default.Close,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.selectedBottomBar,
                modifier = Modifier
                    .size(MaterialTheme.spacing.semiLarge)
            )

        }

    }
}

@Composable
private fun ProfileUserInfoSection(){

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiMedium))

    Text(
        text = "رضا سلیمانی",
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.darkText,
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(2.dp))

    Text(
        text = engToFa(USER_PHONE),
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.SemiBold,
        color = Color.Gray,
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiMedium))

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .weight(0.49f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    model = R.drawable.digi_score
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(42.dp)
            )

            Column(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.small
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "75 ",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.semiDarkText,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(id = R.string.score),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.semiDarkText,
                        textAlign = TextAlign.Center
                    )

                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                Text(
                    text = stringResource(id = R.string.digikala_score),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.darkText,
                    textAlign = TextAlign.Center
                )

            }

        }

        Divider(
            modifier = Modifier
                .height(60.dp)
                .width(2.dp)
                .alpha(0.2f),
            color = Color.LightGray,
            thickness = 2.dp
        )

        Column(
            modifier = Modifier
                .weight(0.49f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    model = R.drawable.digi_wallet
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(34.dp)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            Text(
                text = stringResource(id = R.string.digikala_wallet_active),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.darkText,
                textAlign = TextAlign.Center
            )

        }

    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

}

@Composable
private fun ProfileMiddleSection(){

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .alpha(0.4f),
        color = Color.LightGray
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.semiLarge
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        RoundedItem(
            title = stringResource(id = R.string.authentication),
            image = rememberAsyncImagePainter(model = R.drawable.digi_user),
            onClick = { }
        )

        RoundedItem(
            title = stringResource(id = R.string.digiclub),
            image = rememberAsyncImagePainter(model = R.drawable.digi_club),
            onClick = { }
        )

        RoundedItem(
            title = stringResource(id = R.string.contact_us),
            image = rememberAsyncImagePainter(model = R.drawable.digi_contact_us),
            onClick = { }
        )

    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .alpha(0.4f),
        color = Color.LightGray
    )

}

@Composable
private fun MyOrdersSection(){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.semiMedium
            ),
        horizontalArrangement = Arrangement.Start
    ) {

        Text(
            text = stringResource(id = R.string.my_orders),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.darkText,
            textAlign = TextAlign.Start
        )

    }

    val myOrdersIconsList = listOf(
        MyOrdersIcons(
            name = stringResource(id = R.string.unpaid),
            image = rememberAsyncImagePainter(model = R.drawable.digi_unpaid)
        ),
        MyOrdersIcons(
            name = stringResource(id = R.string.processing),
            image = rememberAsyncImagePainter(model = R.drawable.digi_processing)
        ),
        MyOrdersIcons(
            name = stringResource(id = R.string.delivered),
            image = rememberAsyncImagePainter(model = R.drawable.digi_delivered)
        ),
        MyOrdersIcons(
            name = stringResource(id = R.string.canceled),
            image = rememberAsyncImagePainter(model = R.drawable.digi_cancel)
        ),
        MyOrdersIcons(
            name = stringResource(id = R.string.returned),
            image = rememberAsyncImagePainter(model = R.drawable.digi_returned),
            lastInRow = true
        )
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.semiMedium),
        verticalAlignment = Alignment.CenterVertically
    ){

        items(myOrdersIconsList){
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            RoundedItemWithBadge(
                title = it.name,
                image = it.image,
                onClick = { /*TODO*/ },
                hasNews = it.hasNews,
                notifCount = it.notifCount
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            if (!it.lastInRow){
                Divider(
                    modifier = Modifier
                        .height(70.dp)
                        .width(1.dp)
                        .alpha(0.4f),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }

    }

}