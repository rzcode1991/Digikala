package com.example.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.basket.Address
import com.example.digikala.data.model.checkout.DayAndDate
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_ADDRESS
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.viewModel.AddressViewModel

@Composable
fun CheckoutAddressSection(
    viewModel: AddressViewModel = hiltViewModel()
){

    var userAddressList by remember {
        mutableStateOf<List<Address>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    var address = stringResource(id = R.string.no_address)
    var addressName = ""
    var addEditAddressTxt = stringResource(id = R.string.add_address)

    val userAddressResult by viewModel.userAddressList.collectAsState()
    when(userAddressResult){
        is NetworkResult.Success -> {
            userAddressList = userAddressResult.data ?: emptyList()
            if (userAddressList.isNotEmpty()){

                address = userAddressList[0].address
                USER_ADDRESS = address
                addressName = userAddressList[0].name
                USER_NAME = addressName
                addEditAddressTxt = stringResource(id = R.string.edit_address)

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

    if (isLoading){
        MyLoading(height = 140.dp, isDark = true)
    }else{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.semiLarge,
                    vertical = MaterialTheme.spacing.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = R.drawable.location),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = MaterialTheme.spacing.medium
                    )
            ) {

                Text(
                    text = stringResource(id = R.string.send_to),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.semiDarkText,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(
                            bottom = MaterialTheme.spacing.small
                        )
                )

                Text(
                    text = address,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Normal,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(
                            bottom = MaterialTheme.spacing.small
                        )
                )

                Text(
                    text = addressName,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.SemiBold
                )

            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = MaterialTheme.spacing.semiMedium,
                    end = MaterialTheme.spacing.medium
                )
                .clickable {
                    // TODO
                },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = addEditAddressTxt,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.darkCyan,
                fontWeight = FontWeight.Light
            )

            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.darkCyan
            )

        }
    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.semiSmall)
            .background(MaterialTheme.colorScheme.searchBarBg)
    )


}