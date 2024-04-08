package com.example.digikala.ui.screens.profile

import android.widget.Toast
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.screens.profile.InputValidator.passwordValidator
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.DataStoreViewModel
import com.example.digikala.viewModel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(Dispatchers.Main){
        viewModel.loginResponseResult.collectLatest { loginResponseResult ->
            when(loginResponseResult){
                is NetworkResult.Success -> {

                    viewModel.loading = false
                    if (loginResponseResult.success == true &&
                        loginResponseResult.data?.token?.isNotBlank() == true
                    ){
                        loginResponseResult.data.let { loginResponse ->
                            dataStore.saveUserId(loginResponse.id)
                            dataStore.saveUserPassword(viewModel.passwordInputRegister)
                            dataStore.saveUserPhone(loginResponse.phone)
                            dataStore.saveUserToken(loginResponse.token)
                        }

                        viewModel.screenState = ProfileScreenState.PROFILE_SCREEN
                        Toast.makeText(
                            context,
                            loginResponseResult.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            context,
                            loginResponseResult.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.login_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.loading = false
                }
                is NetworkResult.Loading -> {}
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 80.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        }
        item {
            Text(
                text = stringResource(id = R.string.set_password_text),
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
                onValueChane = {},
                hint = stringResource(id = R.string.phone_and_email),
                enabled = !viewModel.loading
            )
        }
        item {
            MyEditText(
                value = viewModel.passwordInputRegister,
                onValueChane = { newValue ->
                    viewModel.passwordInputRegister = newValue
                },
                hint = stringResource(id = R.string.set_password),
                enabled = !viewModel.loading
            )
        }
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
        item {
            MyButton(
                onClick = {
                    if (passwordValidator(viewModel.passwordInputRegister)) {
                        viewModel.login()
                    } else {
                        Toast.makeText(
                            context,
                            context.resources.getText(R.string.password_format_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                text = stringResource(id = R.string.lets_go),
                isLoading = viewModel.loading
            )
        }

    }


}