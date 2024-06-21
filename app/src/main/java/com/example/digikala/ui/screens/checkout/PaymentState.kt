package com.example.digikala.ui.screens.checkout

import android.content.Context
import com.example.digikala.data.model.checkout.ConfirmPurchaseRequest
import com.example.digikala.data.model.zarinpal.PaymentRequest
import com.example.digikala.data.model.zarinpal.VerifyRequest
import com.example.digikala.viewModel.CheckoutViewModel

sealed class PaymentState {


    data class RequestPayment(
        val paymentRequest: PaymentRequest
    ) : PaymentState()

    data class GoToPay(
        val url: String,
        val context: Context
    ) : PaymentState()

    data class PaymentOK(
        val confirmPurchase: ConfirmPurchaseRequest,
        val checkOutViewModel: CheckoutViewModel
    ) : PaymentState()

    data class VerifyPayment(
        val verifyRequest: VerifyRequest,
        val orderId: String
    ): PaymentState()


}