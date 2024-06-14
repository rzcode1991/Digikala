package com.example.digikala.ui.screens.profile.userInfo

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.profile.userInfo.UserInfoRequest
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.TopSectionWithBackArrowAndText
import com.example.digikala.ui.screens.profile.MyButton
import com.example.digikala.ui.screens.profile.MyEditText
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.DataStoreViewModel
import com.example.digikala.viewModel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UserInfoScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val savedUserName = dataStore.getUserName()

    var isLoading by remember {
        mutableStateOf(false)
    }
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }

    if (savedUserName != "user_name" && savedUserName != null){
        firstName = savedUserName.split(" - ")[0]
        lastName = savedUserName.split(" - ")[1]
    }

    LaunchedEffect(true){
        viewModel.setUserNameResult.collectLatest { userNameResult ->
            when(userNameResult){
                is NetworkResult.Success -> {
                    dataStore.saveUserName("$firstName - $lastName")
                    USER_NAME = "$firstName - $lastName"
                    navController.popBackStack()
                    isLoading = false
                }
                is NetworkResult.Error -> {
                    isLoading = false
                    Toast.makeText(context, context.getString(R.string.save_info_err), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopSectionWithBackArrowAndText(
                navController = navController,
                title = stringResource(id = R.string.user_info)
            )
        },
        bottomBar = {
            MyButton(
                onClick = {

                    isLoading = true

                    val userInfoRequest = UserInfoRequest(
                        token = USER_TOKEN,
                        name = "$firstName - $lastName"
                    )

                    viewModel.setUserName(userInfoRequest)

                },
                text = stringResource(id = R.string.confirm_info),
                isLoading = isLoading
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = stringResource(id = R.string.enter_your_name),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkText
            )

            MyTextInput(
                title = stringResource(id = R.string.first_name),
                value = firstName,
                onValueChange = {
                    firstName = it
                }
            )

            MyTextInput(
                title = stringResource(id = R.string.last_name),
                value = lastName,
                onValueChange = {
                    lastName = it
                }
            )

        }

    }

}

@Composable
private fun MyTextInput(
    title: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.extraSmall
            )
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.darkText,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.extraSmall,
                    bottom = MaterialTheme.spacing.extraSmall
                )
        )

        MyEditText(
            value = value,
            onValueChane = {
                onValueChange(it)
            },
            hint = "",
            enabled = true,
            startPadding = 0.dp,
            endPadding = 0.dp,
            topPadding = 0.dp,
            bottomPadding = 0.dp,
            height = 55.dp
        )

    }

}