package com.example.digikala.repository

import com.example.digikala.data.db.RefIdDao
import com.example.digikala.data.model.zarinpal.PaymentRequest
import com.example.digikala.data.model.zarinpal.RefId
import com.example.digikala.data.model.zarinpal.VerifyRequest
import com.example.digikala.data.model.zarinpal.ZarinVerifyResponse
import com.example.digikala.data.model.zarinpal.ZarinpalPaymentResponse
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.ZarinpalApiInterface
import retrofit2.Response
import javax.inject.Inject

class ZarinpalRepository @Inject constructor(
    private val api: ZarinpalApiInterface,
    private val dao: RefIdDao
): BaseApiResponse() {

    suspend fun requestPayment(request: PaymentRequest): Response<ZarinpalPaymentResponse> {
        return api.paymentRequest(request)
    }

    suspend fun requestVerify(request: VerifyRequest): Response<ZarinVerifyResponse>{
        return api.verifyPayment(request)
    }

    suspend fun insertRefId(refId: RefId) {
        dao.insertRefId(refId)
    }

    /*suspend fun getRefIdByOrderId(orderId: String){
        dao.getRefIdByOrderId(orderId)
    }*/


}