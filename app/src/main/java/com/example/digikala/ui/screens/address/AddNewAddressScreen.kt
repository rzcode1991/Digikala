package com.example.digikala.ui.screens.address

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.address.AddAddressRequest
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.TopSectionWithBackArrowAndText
import com.example.digikala.ui.screens.profile.MyButton
import com.example.digikala.ui.screens.profile.MyEditText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.AddressViewModel

@Composable
fun AddNewAddressScreen(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var address by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var phone by remember {
        mutableStateOf("")
    }
    var postalCode by remember {
        mutableStateOf("")
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val addAddressResult by viewModel.addAddressResult.collectAsState()
    when(addAddressResult){
        is NetworkResult.Success -> {
            LaunchedEffect(true){
                navController.popBackStack()
                Toast.makeText(
                    context,
                    context.getString(R.string.address_added_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            }
            isLoading = false
        }
        is NetworkResult.Error -> {
            Toast.makeText(
                context,
                addAddressResult.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.e("my_tag", "addAddressResult err: ${addAddressResult.message}")
            isLoading = false
        }
        is NetworkResult.Loading -> {
        }
    }

    Scaffold(
        topBar = {
            TopSectionWithBackArrowAndText(
                navController = navController,
                title = stringResource(id = R.string.add_address)
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            MyEditText(
                value = address,
                onValueChane = {
                    address = it
                },
                hint = stringResource(id = R.string.address),
                enabled = !isLoading,
                isSingleLine = false,
                maxLines = 3
            )

            MyEditText(
                value = name,
                onValueChane = {
                    name = it
                },
                hint = stringResource(id = R.string.name),
                enabled = !isLoading
            )

            MyEditText(
                value = phone,
                onValueChane = {
                    phone = it
                },
                hint = stringResource(id = R.string.phone),
                enabled = !isLoading
            )

            MyEditText(
                value = postalCode,
                onValueChane = {
                    postalCode = it
                },
                hint = stringResource(id = R.string.postal_code),
                enabled = !isLoading
            )

            MyButton(
                onClick = {
                    if (inputValidator(address, name, phone, postalCode)){
                        isLoading = true
                        viewModel.saveUserAddress(
                            AddAddressRequest(
                                token = USER_TOKEN,
                                address = address,
                                name = name,
                                phone = phone,
                                postalCode = postalCode
                            )
                        )
                    }else{
                        Toast.makeText(
                            context,
                            context.getString(R.string.invalid_user_input),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                text = stringResource(id = R.string.save_address),
                isLoading = isLoading
            )

        }

    }

}

private fun inputValidator(
    address: String,
    name: String,
    phone: String,
    postalCode: String
): Boolean{

    return (
            address.isNotBlank() &&
            name.isNotBlank() &&
            phone.isNotBlank() &&
            postalCode.isNotBlank()
            )

}