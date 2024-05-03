package com.example.digikala.utils

import android.app.Activity
import android.util.Log
import com.example.digikala.utils.Constants.ZARINPAL_MERCHANT_ID
import com.zarinpal.ZarinPalBillingClient
import com.zarinpal.billing.purchase.Purchase
import com.zarinpal.client.BillingClientStateListener
import com.zarinpal.client.ClientState
import com.zarinpal.provider.core.future.FutureCompletionListener
import com.zarinpal.provider.core.future.TaskResult
import com.zarinpal.provider.model.response.Receipt

object ZarinpalPurchase {


    private val stateListener = object : BillingClientStateListener {
        override fun onClientServiceDisconnected() {
            Log.e("my_tag", "Billing client Disconnected")
        }

        override fun onClientSetupFinished(state: ClientState) {
        }
    }

    fun purchase(
        activity: Activity,
        amount: Long,
        description: String,
        onPurchaseCompleted: (String) -> Unit
    ){

        val client = ZarinPalBillingClient.newBuilder(activity)
            .enableShowInvoice()
            .setListener(stateListener)
            .build()

        val purchase = Purchase.newBuilder()
            .asPaymentRequest(
                ZARINPAL_MERCHANT_ID,
                amount,
                "http://yoursite.com/verify",
                description
            )
            .build()

        client.launchBillingFlow(
            purchase,
            object : FutureCompletionListener<Receipt> {
                override fun onComplete(task: TaskResult<Receipt>) {
                    if (task.isSuccess) {
                        val receipt = task.success
                        Log.e("my_tag", "task success, receipt is: $receipt")
                        receipt?.transactionID?.let {
                            Log.e("my_tag", "transaction id is: $it")
                            onPurchaseCompleted(it)
                        }
                    } else {
                        Log.e("my_tag", "task NOK")
                        task.failure?.printStackTrace()
                    }
                }
            }
        )

    }


}