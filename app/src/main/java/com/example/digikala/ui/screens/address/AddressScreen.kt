package com.example.digikala.ui.screens.address

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.address.Address
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.components.TopSectionWithBackArrowAndText
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaLightRed
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_ADDRESS
import com.example.digikala.viewModel.AddressViewModel
import com.example.digikala.viewModel.DataStoreViewModel

@Composable
fun AddressScreen(
    navController: NavHostController,
    addressViewModel: AddressViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
){

    LaunchedEffect(Unit){
        addressViewModel.getUserAddressList()
    }

    var selectedAddressIndex by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {
        selectedAddressIndex = dataStoreViewModel.getSelectedAddressIndex() ?: 0
    }

    var userAddressList by remember {
        mutableStateOf<List<Address>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val userAddressResult by addressViewModel.userAddressList.collectAsState()
    when(userAddressResult){
        is NetworkResult.Success -> {
            userAddressList = userAddressResult.data ?: emptyList()
            if (userAddressList.isNotEmpty()){

                USER_ADDRESS = userAddressList[selectedAddressIndex].address

            }
            isLoading = false
        }
        is NetworkResult.Error -> {
            Log.e("userAddressResult err", userAddressResult.message.toString())
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
    }

    Scaffold(
        topBar = {
            TopSectionWithBackArrowAndText(
                navController = navController,
                title = stringResource(id = R.string.addresses)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                navController.navigate(Screen.AddNewAddress.route)
                },
                modifier = Modifier
                    .padding(
                        bottom = MaterialTheme.spacing.medium
                    ),
                containerColor = MaterialTheme.colorScheme.digikalaLightRed,
                contentColor = MaterialTheme.colorScheme.darkText
            ) {

                Icon(
                    Icons.Default.AddLocationAlt,
                    contentDescription = ""
                )

            }
        }
    ) { paddingValues ->

        if (isLoading){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.medium)
                    .padding(paddingValues)
            ) {
                MyLoading(height = 300.dp, isDark = !isSystemInDarkTheme())
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.medium)
                    .padding(paddingValues)
            ){

                if (userAddressList.isEmpty()){
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {

                            Text(
                                text = stringResource(id = R.string.no_address),
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.darkText,
                                fontWeight = FontWeight.Normal
                            )

                        }
                    }
                }else{
                    itemsIndexed(userAddressList){ index , address ->
                        AddressView(
                            address = address,
                            isSelected = selectedAddressIndex == index,
                            onClick = {
                                selectedAddressIndex = index
                                dataStoreViewModel.saveSelectedAddressIndex(index)
                                USER_ADDRESS = address.address
                            }
                        )
                    }
                }

            }
        }

    }


}