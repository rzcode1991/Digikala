package com.example.digikala.ui.screens.profile.settings

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.HelpCenter
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.MainActivity
import com.example.digikala.R
import com.example.digikala.data.model.profile.settings.SettingsItem
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.screens.profile.ProfileScreenState
import com.example.digikala.ui.screens.profile.RowWithIconAndTextItemView
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.DIGI_BUG
import com.example.digikala.utils.Constants.DIGI_FAQ
import com.example.digikala.utils.Constants.DIGI_PRIVACY
import com.example.digikala.utils.Constants.DIGI_SCORE
import com.example.digikala.utils.Constants.DIGI_TERMS
import com.example.digikala.utils.Constants.ENGLISH_LANG
import com.example.digikala.utils.Constants.MY_WEBSITE
import com.example.digikala.utils.Constants.PERSIAN_LANG
import com.example.digikala.utils.Constants.USER_ADDRESS
import com.example.digikala.utils.Constants.USER_ID
import com.example.digikala.utils.Constants.USER_LANGUAGE
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.utils.Constants.USER_PASSWORD
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.utils.LocaleUtils
import com.example.digikala.viewModel.BasketViewModel
import com.example.digikala.viewModel.DataStoreViewModel
import com.example.digikala.viewModel.FavoritesViewModel
import com.example.digikala.viewModel.ProfileViewModel

@Composable
fun SettingsScreen(
    navController: NavHostController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()

) {

    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

    val activity = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.settings),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold
            )

            IconButton(
                onClick = {
                    navController.navigateUp()
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

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        val items = listOf(
            SettingsItem(
                image = {
                    Icon(
                        Icons.AutoMirrored.Outlined.HelpCenter,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                titleId = R.string.repetitive_questions,
                url = DIGI_FAQ
            ),
            SettingsItem(
                image = {
                    Icon(
                        Icons.Outlined.PrivacyTip,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                titleId = R.string.privacy,
                url = DIGI_PRIVACY
            ),
            SettingsItem(
                image = {
                    Icon(
                        Icons.AutoMirrored.Outlined.Assignment,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                titleId = R.string.terms_of_use,
                url = DIGI_TERMS
            ),
            SettingsItem(
                image = {
                    Icon(
                        Icons.Outlined.Call,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                titleId = R.string.contact_us,
                url = MY_WEBSITE
            ),
            SettingsItem(
                image = {
                    Icon(
                        Icons.Outlined.BugReport,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                titleId = R.string.error_report,
                url = DIGI_BUG
            ),
            SettingsItem(
                image = {
                    Icon(
                        Icons.Outlined.StarOutline,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                titleId = R.string.rate_to_digikala,
                url = DIGI_SCORE
            ),
            SettingsItem(
                image = {
                    Icon(
                        Icons.Outlined.Language,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                titleId = R.string.changing_lang,
                url = "",
                extraComposable = {
                    ChangeLanguageSwitch(dataStoreViewModel, activity)
                }
            )
        )

        items.forEach { item ->
            RowWithIconAndTextItemView(
                lastItem = false,
                myImage = item.image,
                extraComposable = item.extraComposable,
                titleId = item.titleId,
                onClick = {
                    if (item.url.isNotEmpty()) {
                        navController.navigate(Screen.WebView.route + "?url=${item.url}")
                    }
                }
            )
        }

        RowWithIconAndTextItemView(
            lastItem = true,
            myImage = {
                Icon(
                    Icons.AutoMirrored.Outlined.Logout,
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp),
                    tint = MaterialTheme.colorScheme.digikalaRed
                )
            },
            titleId = R.string.sign_out,
            color = MaterialTheme.colorScheme.digikalaRed,
            extraComposable = {},
            onClick = {
                logOut(
                    navController = navController,
                    dataStoreViewModel = dataStoreViewModel,
                    profileViewModel = profileViewModel,
                    basketViewModel = basketViewModel,
                    favoritesViewModel = favoritesViewModel
                )
            }
        )

    }

}

@Composable
private fun ChangeLanguageSwitch(
    dataStoreViewModel: DataStoreViewModel,
    activity: Activity
){

    var language by remember {
        mutableStateOf(dataStoreViewModel.getUserLanguage())
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(id = R.string.english),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Switch(
            checked = language == PERSIAN_LANG,
            onCheckedChange = {
                language = if (language == PERSIAN_LANG){
                    ENGLISH_LANG
                }else{
                    PERSIAN_LANG
                }
                dataStoreViewModel.saveUserLanguage(language ?: PERSIAN_LANG)
                activity.apply {
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            },
            colors = SwitchDefaults.colors(
                checkedBorderColor = MaterialTheme.colorScheme.darkCyan,
                uncheckedBorderColor = MaterialTheme.colorScheme.darkCyan,
                checkedThumbColor = Color.White,
                uncheckedThumbColor = Color.White,
                checkedTrackColor = MaterialTheme.colorScheme.darkCyan,
                uncheckedTrackColor = MaterialTheme.colorScheme.darkCyan
            )
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Text(
            text = stringResource(id = R.string.persian),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

    }

}


private fun logOut(
    navController: NavHostController,
    dataStoreViewModel: DataStoreViewModel,
    profileViewModel: ProfileViewModel,
    basketViewModel: BasketViewModel,
    favoritesViewModel: FavoritesViewModel
){

    dataStoreViewModel.clearDataStore()

    USER_TOKEN = ""
    USER_PHONE = ""
    USER_ID = "USER_ID"
    USER_PASSWORD = ""
    USER_ADDRESS = ""
    USER_NAME = "user_name"

    basketViewModel.clearAllCartItems()

    favoritesViewModel.clearFavoriteList()

    profileViewModel.screenState = ProfileScreenState.LOGIN_SCREEN

    navController.navigate(Screen.Profile.route){
        popUpTo(0){
            inclusive = true
        }
    }

}