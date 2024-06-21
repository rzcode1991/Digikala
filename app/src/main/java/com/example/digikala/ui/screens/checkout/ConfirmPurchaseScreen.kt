package com.example.digikala.ui.screens.checkout

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.digikala.ui.theme.darkGreen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants
import com.example.digikala.utils.Constants.AUTHORITY_FROM_CALLBACK
import com.example.digikala.utils.Constants.STATUS_FROM_CALLBACK
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.utils.Constants.ZARINPAL_MERCHANT_ID
import com.example.digikala.utils.Constants.ZARINPAL_PAYMENT_URL
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma
import com.example.digikala.utils.LocaleUtils
import com.example.digikala.viewModel.BasketViewModel
import com.example.digikala.viewModel.CheckoutViewModel
import com.example.digikala.viewModel.ZarinpalViewModel

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
    val context = LocalContext.current

    val paymentRequest = PaymentRequest(
        merchant_id = ZARINPAL_MERCHANT_ID,
        amount = orderPrice.toInt() * 10,
        callback_url = "myapp://purchase",
        description = "test description",
        metadata = mapOf(
            "mobile" to USER_PHONE,
            "email" to "test@gmail.com"
        )
    )

    var orderStateText by remember {
        mutableStateOf("")
    }
    var orderStateTextColor by remember {
        mutableStateOf(Color.Black)
    }

    val authority by zarinViewModel.authority.collectAsState()

    if (AUTHORITY_FROM_CALLBACK.isEmpty()){

        STATUS_FROM_CALLBACK = ""
        AUTHORITY_FROM_CALLBACK = ""
        LaunchedEffect(true){
            zarinViewModel.clearAuthority()
            zarinViewModel.clearTransactionState()
        }

        orderStateText = stringResource(id = R.string.waiting_for_pay)
        orderStateTextColor = MaterialTheme.colorScheme.darkText
        when(authority){
            "" -> {

            }
            else -> {
                val url = "$ZARINPAL_PAYMENT_URL$authority"
                LaunchedEffect(true){
                    zarinViewModel.onEvent(PaymentState.GoToPay(url, context))
                }
            }
        }
    }else{
        zarinViewModel.isLoading = true

        if (STATUS_FROM_CALLBACK == "OK"){

            orderStateText = stringResource(id = R.string.waiting_for_pay)
            orderStateTextColor = MaterialTheme.colorScheme.darkText

            LaunchedEffect(true){

                val verifyRequest = VerifyRequest(
                    merchant_id = ZARINPAL_MERCHANT_ID,
                    amount = orderPrice.toInt() * 10,
                    authority = AUTHORITY_FROM_CALLBACK
                )

                zarinViewModel.onEvent(PaymentState.VerifyPayment(
                    verifyRequest = verifyRequest,
                    orderId = orderId
                ))

            }

            val transactionFinishOK by zarinViewModel.transactionFinishOK.collectAsState()

            when{
                transactionFinishOK -> {

                    LaunchedEffect(true){

                        zarinViewModel.clearAuthority()
                        basketViewModel.deleteAllItems()
                        Toast.makeText(
                            context,
                            context.getString(R.string.payment_success),
                            Toast.LENGTH_SHORT
                        ).show()

                        val confirmPurchaseRequest = ConfirmPurchaseRequest(
                            orderId = orderId,
                            token = USER_TOKEN,
                            transactionId = "Paid"
                        )
                        zarinViewModel.onEvent(PaymentState.PaymentOK(
                            confirmPurchase = confirmPurchaseRequest,
                            checkOutViewModel = checkoutViewModel
                        ))
                    }

                    zarinViewModel.isLoading = false
                    orderStateText = stringResource(id = R.string.paid)
                    orderStateTextColor = MaterialTheme.colorScheme.darkGreen

                    /*val confirmPurchaseResult by checkoutViewModel.confirmPurchaseResult.collectAsState()

                    when(confirmPurchaseResult){
                        is NetworkResult.Loading -> {

                        }
                        is NetworkResult.Success -> {

                        }
                        is NetworkResult.Error -> {
                            STATUS_FROM_CALLBACK = ""
                            AUTHORITY_FROM_CALLBACK = ""
                            LaunchedEffect(true){
                                zarinViewModel.clearAuthority()
                                zarinViewModel.clearTransactionState()
                            }

                            zarinViewModel.isLoading = false
                            Toast.makeText(
                                context,
                                context.getString(R.string.error_happened),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }*/
                }
            }

        }else if (STATUS_FROM_CALLBACK == "NOK"){

            orderStateText = stringResource(id = R.string.pay_failed)
            orderStateTextColor = MaterialTheme.colorScheme.digikalaRed

            LaunchedEffect(true){
                zarinViewModel.clearAuthority()
                zarinViewModel.clearTransactionState()
            }

            zarinViewModel.isLoading = false
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
                text = orderStateText,
                color = orderStateTextColor,
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

        if (AUTHORITY_FROM_CALLBACK.isEmpty()){
            Button(
                onClick = {
                    zarinViewModel.isLoading = true
                    zarinViewModel.onEvent(PaymentState.RequestPayment(paymentRequest))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.digikalaRed
                ),
                shape = MaterialTheme.roundedShape.small,
                enabled = !zarinViewModel.isLoading
            ) {
                if (zarinViewModel.isLoading) {
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
        }else{
            Button(
                onClick = {
                    STATUS_FROM_CALLBACK = ""
                    AUTHORITY_FROM_CALLBACK = ""
                    zarinViewModel.clearAuthority()
                    zarinViewModel.clearTransactionState()
                    navController.navigate(Screen.Profile.route){
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.digikalaRed
                ),
                shape = MaterialTheme.roundedShape.small,
                enabled = !zarinViewModel.isLoading
            ) {
                if (zarinViewModel.isLoading) {
                    MyLoadingButton()
                } else {
                    Text(
                        text = stringResource(id = R.string.go_to_profile),
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
        }

        Button(
            onClick = {
                STATUS_FROM_CALLBACK = ""
                AUTHORITY_FROM_CALLBACK = ""
                zarinViewModel.clearAuthority()
                zarinViewModel.clearTransactionState()
                navController.navigate(Screen.Home.route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.bottomBarColor
            ),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.digikalaRed),
            shape = MaterialTheme.roundedShape.small,
            enabled = !zarinViewModel.isLoading
        ) {
            if (zarinViewModel.isLoading){
                MyLoadingButton()
            }else{
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

}

@Composable
private fun MyLoadingButton() {
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