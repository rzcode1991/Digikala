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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.digikala.viewModel.ProfileViewModel

@Composable
fun RegisterScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current

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
                hint = stringResource(id = R.string.phone_and_email)
            )
        }
        item {
            MyEditText(
                value = viewModel.passwordInputRegister,
                onValueChane = { newValue ->
                    viewModel.passwordInputRegister = newValue
                },
                hint = stringResource(id = R.string.set_password)
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
                text = stringResource(id = R.string.lets_go)
            )
        }

    }

    val loginResponseResult by viewModel.loginResponseResult.collectAsState()
    var loading by remember {
        mutableStateOf(false)
    }
    when(loginResponseResult){
        is NetworkResult.Success -> {
            loading = false
            if (loginResponseResult.success == true){
                viewModel.screenState = ProfileScreenState.PROFILE_SCREEN
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
            loading = false
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }


}