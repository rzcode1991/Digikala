package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.profile.MyOrdersIcons
import com.example.digikala.data.model.profile.RowWithIconAndTextItem
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.CenterBannerItem
import com.example.digikala.ui.components.RoundedItem
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants
import com.example.digikala.utils.Constants.DIGI_PLUS_URL
import com.example.digikala.utils.Constants.DIGI_WALLET
import com.example.digikala.utils.Constants.MY_WEBSITE
import com.example.digikala.utils.Constants.PERSIAN_LANG
import com.example.digikala.utils.Constants.USER_LANGUAGE
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.utils.DigitHelper.engToFa
import com.example.digikala.utils.LocaleUtils
import com.example.digikala.viewModel.DataStoreViewModel

@Composable
fun Profile(
    navController: NavHostController
) {

    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 80.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            ProfileTopBarSection(navController = navController)
        }
        item {
            ProfileUserInfoSection(navController = navController)
        }
        item {
            ProfileMiddleSection(navController)
        }
        item {
            MyOrdersSection()
        }
        item {
            CenterBannerItem(
                navController = navController,
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
                image = {
                    Image(
                        painter = rememberAsyncImagePainter(model = R.drawable.digi_plus_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                route = Screen.WebView.route + "?url=${DIGI_PLUS_URL}"
            ),
            RowWithIconAndTextItem(
                titleId = R.string.fav_list,
                image = {
                    Image(
                        painter = rememberAsyncImagePainter(model = R.drawable.digi_fav_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                route = Screen.FavoriteScreen.route
            ),
            RowWithIconAndTextItem(
                titleId = R.string.my_comments,
                image = {
                    Image(
                        painter = rememberAsyncImagePainter(model = R.drawable.digi_comments_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                route = ""
            ),
            RowWithIconAndTextItem(
                titleId = R.string.addresses,
                image = {
                    Image(
                        painter = rememberAsyncImagePainter(model = R.drawable.digi_adresses_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                route = ""
            ),
            RowWithIconAndTextItem(
                titleId = R.string.profile_info,
                image = {
                    Image(
                        painter = rememberAsyncImagePainter(model = R.drawable.digi_profile_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                lastItem = true,
                route = Screen.UserInfo.route
            )
        )

        items(rowWithIconAndTextItems) {
            RowWithIconAndTextItemView(
                lastItem = it.lastItem,
                myImage = it.image,
                titleId = it.titleId,
                onClick = {
                    if (it.route.isNotEmpty()) {
                        navController.navigate(it.route)
                    }
                }
            )
        }

        item {
            CenterBannerItem(
                navController = navController,
                imageId = R.drawable.digiclub2,
                height = 200
            )
        }


    }

}

@Composable
private fun ProfileTopBarSection(
    navController: NavHostController
) {
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
            onClick = {
                navController.navigate(Screen.SettingScreen.route)
            }
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
            onClick = {
                navController.navigate(Screen.Home.route)
            }
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
private fun ProfileUserInfoSection(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel()
) {

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiMedium))

    var text by remember {
        mutableStateOf("")
    }
    var color: Color = MaterialTheme.colorScheme.darkText
    var modifier: Modifier = Modifier

    val userName = dataStore.getUserName()
    if (userName != "user_name" && userName != null) {
        val firstName = userName.split(" - ")[0]
        val lastName = userName.split(" - ")[1]
        text = if (USER_LANGUAGE == PERSIAN_LANG) {
            "$firstName $lastName"
        } else {
            "$lastName $firstName"
        }
    } else {
        text = stringResource(id = R.string.add_user_info)
        color = MaterialTheme.colorScheme.darkCyan
        modifier = Modifier.clickable {
            navController.navigate(Screen.UserInfo.route)
        }
    }

    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        color = color,
        textAlign = TextAlign.Center,
        modifier = modifier
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
                .weight(0.49f)
                .clickable {
                    navController.navigate(Screen.WebView.route + "?url=${DIGI_WALLET}")
                },
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
private fun ProfileMiddleSection(
    navController: NavHostController
) {

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
            onClick = {
                navController.navigate(Screen.UserInfo.route)
            }
        )

        RoundedItem(
            title = stringResource(id = R.string.digiclub),
            image = rememberAsyncImagePainter(model = R.drawable.digi_club),
            onClick = {
                navController.navigate(Screen.WebView.route + "?url=${Constants.DIGI_CLUB}")
            }
        )

        RoundedItem(
            title = stringResource(id = R.string.contact_us),
            image = rememberAsyncImagePainter(model = R.drawable.digi_contact_us),
            onClick = {
                navController.navigate(Screen.WebView.route + "?url=${MY_WEBSITE}")
            }
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
private fun MyOrdersSection() {

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
    ) {

        items(myOrdersIconsList) {
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            RoundedItemWithBadge(
                title = it.name,
                image = it.image,
                onClick = { /*TODO*/ },
                hasNews = it.hasNews,
                notifCount = it.notifCount
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            if (!it.lastInRow) {
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