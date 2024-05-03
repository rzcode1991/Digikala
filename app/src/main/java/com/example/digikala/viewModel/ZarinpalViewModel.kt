package com.example.digikala.viewModel

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.zarinpal.PaymentRequest
import com.example.digikala.data.model.zarinpal.RefId
import com.example.digikala.data.model.zarinpal.VerifyRequest
import com.example.digikala.repository.ZarinpalRepository
import com.example.digikala.utils.ZarinpalPurchase
import com.google.gson.Gson
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZarinpalViewModel @Inject constructor(
    private val repository: ZarinpalRepository
) : ViewModel() {

    val authority = MutableStateFlow("")
    val authorityFromCallback = MutableStateFlow("")
    val statusFromCallback = MutableStateFlow("")
    val shouldUserGoToWebView = MutableStateFlow(true)
    val refId = MutableStateFlow(0)

    private fun saveRefId(refId: RefId){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRefId(refId)
        }
    }

    fun changeShouldUserGoToWebView(){
        viewModelScope.launch {
            shouldUserGoToWebView.emit(false)
        }
    }

    fun emitAuthorityFromCallback(newValue: String){
        viewModelScope.launch {
            authorityFromCallback.emit(newValue)
        }
    }

    fun changeStatusFromCallback(newValue: String){
        viewModelScope.launch {
            statusFromCallback.emit(newValue)
        }
    }

    fun requestPayment(request: PaymentRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.requestPayment(request)
                when {
                    response.isSuccessful && response.body() != null -> {
                        val paymentData = response.body()?.data
                        if (paymentData != null) {
                            if (paymentData.code == 100 && paymentData.authority.isNotEmpty()) {
                                authority.emit(paymentData.authority)
                                Log.e("my_tag", "payment data is: $paymentData")
                            }
                        } else {
                            Log.e("my_tag", "Unexpected paymentData body")
                        }
                    }

                    else -> {
                        response.errorBody()?.let { errorBody ->
                            val jsonString = errorBody.string()
                            val jsonObject = JsonParser.parseString(jsonString).asJsonObject
                            val gson = Gson()
                            if (jsonObject.has("errors")) {
                                val errorObject = jsonObject.getAsJsonObject("errors")

                                val code = errorObject.getAsJsonPrimitive("code").asInt
                                Log.e("my_tag", "Error Code: $code")

                                val message = errorObject.getAsJsonPrimitive("message").asString
                                Log.e("my_tag", "Error Message: $message")

                                val validations = gson.fromJson(
                                    errorObject.getAsJsonArray("validations"),
                                    Array<Any>::class.java
                                )
                                Log.e("my_tag", "Error Validations: ${validations.joinToString()}")
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("my_tag", "requestPayment err: ${e.message.toString()}")
            }
        }
    }

    fun requestVerify(request: VerifyRequest, orderId: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.requestVerify(request)
                when{
                    response.isSuccessful && response.body() != null -> {
                        val verifyData = response.body()?.data
                        if (verifyData != null) {
                            Log.e("my_tag", "verify data is: $verifyData")
                            if (verifyData.code == 100) {
                                refId.emit(verifyData.refId)
                                val refId = RefId(orderId, verifyData.refId)
                                saveRefId(refId)
                            }
                        } else {
                            Log.e("my_tag", "Unexpected verifyData body")
                        }
                    }
                    else -> {
                        response.errorBody()?.let { errorBody ->
                            Log.e("my_tag", errorBody.toString())
                        }
                    }
                }
            }catch (e: Exception){
                Log.e("my_tag", "requestVerify err: ${e.message.toString()}")
            }
        }
    }


    @Composable
    fun launchZarinPalPurchaseBottomSheet() {

        val context = LocalContext.current
        val activity = context as Activity

        try {
            ZarinpalPurchase.purchase(
                activity,
                1000,
                "test description"
            ) {
                Log.e("my_tag", "Transaction ID : $it")
            }
        } catch (e: Exception) {
            Log.e("my_tag", "launchZarinPalPurchaseBottomSheet err, exception is: $e")
            e.printStackTrace()
        }
    }


}