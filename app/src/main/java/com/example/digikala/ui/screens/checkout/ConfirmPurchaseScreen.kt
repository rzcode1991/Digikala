package com.example.digikala.ui.screens.checkout

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.checkout.ConfirmPurchaseRequest
import com.example.digikala.data.model.zarinpal.PaymentRequest
import com.example.digikala.data.model.zarinpal.VerifyRequest
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.Loading3Dots
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaLightGreen
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.utils.Constants.ZARINPAL_MERCHANT_ID
import com.example.digikala.utils.Constants.ZARINPAL_PAYMENT_URL
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma
import com.example.digikala.utils.LocaleUtils
import com.example.digikala.viewModel.BasketViewModel
import com.example.digikala.viewModel.CheckoutViewModel
import com.example.digikala.viewModel.ZarinpalViewModel
import kotlinx.coroutines.launch

@Composable
fun ConfirmPurchaseScreen(
    navController: NavHostController,
    orderId: String,
    orderPrice: String,
    zarinViewModel: ZarinpalViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {

    LocaleUtils.setLocale(LocalContext.current, Constants.USER_LANGUAGE)

    val scope = rememberCoroutineScope()

    val paymentRequest = PaymentRequest(
        merchant_id = ZARINPAL_MERCHANT_ID,
        amount = orderPrice.toInt(),
        callback_url = "myapp://purchase",
        description = "test description",
        metadata = mapOf(
            "mobile" to USER_PHONE,
            "email" to "test@gmail.com"
        )
    )

    var paymentState by remember {
        mutableStateOf(2)
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val shouldUserGoToWebView by zarinViewModel.shouldUserGoToWebView.collectAsState()

    if (shouldUserGoToWebView) {
        val authority by zarinViewModel.authority.collectAsState()
        Log.e("my_tag", "authority is: $authority")
        when {
            authority.isNotEmpty() -> {
                val url = "$ZARINPAL_PAYMENT_URL$authority"
                navController.navigate(Screen.WebView.route + "?url=${Uri.encode(url)}")
                zarinViewModel.changeShouldUserGoToWebView()
            }
        }
    }

    val refId by zarinViewModel.refId.collectAsState()

    if (!shouldUserGoToWebView && zarinViewModel.statusFromCallback.value.isEmpty() && !isLoading) {
        paymentState = 0
    } else if (zarinViewModel.statusFromCallback.value == "OK") {
        paymentState = 1
        val verifyRequest = VerifyRequest(
            merchant_id = ZARINPAL_MERCHANT_ID,
            amount = orderPrice.toInt() * 10,
            authority = zarinViewModel.authorityFromCallback.value
        )
        zarinViewModel.requestVerify(verifyRequest, orderId)
        basketViewModel.deleteAllItems()
        when{
            refId != 0 -> {
                checkoutViewModel.confirmPurchase(
                    ConfirmPurchaseRequest(
                        orderId = orderId,
                        token = USER_TOKEN,
                        transactionId = refId.toString()
                    )
                )
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.price_to_pay),
                color = MaterialTheme.colorScheme.semiDarkText,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = engToFaAndSeparateByComma(orderPrice),
                color = MaterialTheme.colorScheme.semiDarkText,
                style = MaterialTheme.typography.headlineMedium
            )

        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small2))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.order_state),
                color = MaterialTheme.colorScheme.semiDarkText,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = when (paymentState) {
                    0 -> {
                        stringResource(id = R.string.pay_failed)
                    }

                    1 -> {
                        stringResource(id = R.string.paid)
                    }

                    else -> {
                        stringResource(id = R.string.waiting_for_pay)
                    }
                },
                color = when (paymentState) {
                    0 -> {
                        MaterialTheme.colorScheme.digikalaRed
                    }

                    1 -> {
                        MaterialTheme.colorScheme.digikalaLightGreen
                    }

                    else -> {
                        MaterialTheme.colorScheme.semiDarkText
                    }
                },
                style = MaterialTheme.typography.headlineMedium
            )

        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small2))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.order_id),
                color = MaterialTheme.colorScheme.semiDarkText,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = orderId,
                color = MaterialTheme.colorScheme.semiDarkText,
                style = MaterialTheme.typography.headlineMedium
            )

        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        if (refId != 0) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(id = R.string.ref_id),
                    color = MaterialTheme.colorScheme.darkText,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = refId.toString(),
                    color = MaterialTheme.colorScheme.darkText,
                    style = MaterialTheme.typography.headlineMedium
                )

            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }

        if (paymentState == 2) {
            Button(
                onClick = {

                    if (zarinViewModel.statusFromCallback.value.isEmpty()) {
                        isLoading = true
                        scope.launch {
                            zarinViewModel.requestPayment(paymentRequest)
                        }
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.digikalaRed
                ),
                shape = MaterialTheme.roundedShape.small
            ) {
                if (isLoading) {
                    MyLoadingButton()
                } else {
                    Text(
                        text = stringResource(id = R.string.pay),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.bottomBarColor,
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.spacing.large,
                                vertical = MaterialTheme.spacing.extraSmall
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        } else if (paymentState == 1) {
            Button(
                onClick = {

                    if (refId != 0) {
                        navController.navigate(Screen.Profile.route){
                            popUpTo(Screen.Profile.route){
                                inclusive = true
                            }
                        }
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.digikalaRed
                ),
                shape = MaterialTheme.roundedShape.small
            ) {
                if (refId != 0){
                    Text(
                        text = stringResource(id = R.string.after_payment),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.bottomBarColor,
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.spacing.large,
                                vertical = MaterialTheme.spacing.extraSmall
                            )
                    )
                }else{
                    MyLoadingButton()
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }

        Button(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.bottomBarColor
            ),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.digikalaRed),
            shape = MaterialTheme.roundedShape.small
        ) {
            Text(
                text = stringResource(id = R.string.go_to_home_screen),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.digikalaRed,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.small,
                        vertical = MaterialTheme.spacing.extraSmall
                    )
            )
        }

    }

}

@Composable
private fun MyLoadingButton(){
    Row(
        modifier = Modifier
            .width(120.dp)
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.semiSmall
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Loading3Dots(isDark = isSystemInDarkTheme())
    }
}