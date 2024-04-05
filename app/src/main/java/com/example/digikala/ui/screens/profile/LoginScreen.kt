package com.example.digikala.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.ui.screens.profile.InputValidator.emailValidator
import com.example.digikala.ui.screens.profile.InputValidator.phoneValidator
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.ProfileViewModel

@Composable
fun LoginScreen(
    viewModel: ProfileViewModel = hiltViewModel()
){

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 80.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        item {
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
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.digi_smile),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        }
        item {
            Text(
                text = stringResource(id = R.string.login_text),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.semiLarge
                    )
            )
        }
        item {
            MyEditText(
                value = viewModel.phoneEmailInput,
                onValueChane = { newValue ->
                    viewModel.phoneEmailInput = newValue
                },
                hint = stringResource(id = R.string.phone_and_email)
            )
        }
        item {
            MyButton(
                onClick = {
                    if (phoneValidator(viewModel.phoneEmailInput)
                        || emailValidator(viewModel.phoneEmailInput)){
                        viewModel.screenState = ProfileScreenState.PROFILE_SCREEN
                    }else{
                        Toast.makeText(
                            context,
                            context.resources.getText(R.string.invalid_phone_or_email),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                text = stringResource(id = R.string.enter_digikala)
            )
        }
        item {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.searchBarBg
            )
        }
        item {
            TermsAndRulesText(
                fullText = stringResource(id = R.string.terms_and_rules_full),
                underlinedTexts = listOf(
                    stringResource(id = R.string.terms_and_rules),
                    stringResource(id = R.string.privacy_and_rules)
                ),
                fontSize = 10.sp,
                textColor = MaterialTheme.colorScheme.semiDarkText,
                textAlign = TextAlign.Center
            )
        }

    }

}