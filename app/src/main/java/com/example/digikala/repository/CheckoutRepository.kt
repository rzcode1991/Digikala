package com.example.digikala.repository

import com.example.digikala.data.db.OrderDao
import com.example.digikala.data.model.checkout.ConfirmPurchaseRequest
import com.example.digikala.data.model.checkout.Order
import com.example.digikala.data.model.checkout.OrderRequest
import com.example.digikala.data.model.checkout.OrderStatus
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.CheckoutApiInterface
import com.example.digikala.data.network.NetworkResult
import javax.inject.Inject

class CheckoutRepository @Inject constructor(
    private val api: CheckoutApiInterface,
    private val orderDao: OrderDao
): BaseApiResponse() {

    suspend fun setNewOrder(orderRequest: OrderRequest): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(orderRequest)
        }

    suspend fun confirmPurchase(confirmPurchase: ConfirmPurchaseRequest): NetworkResult<String> =
        safeApiCall {
            api.confirmPurchase(confirmPurchase)
        }

    suspend fun addNewOrder(order: Order){
        orderDao.addNewOrder(order)
    }

    val allNotPaidYetOrders = orderDao.getAllOrders(OrderStatus.NOT_PAID_YET)
    val allWaitingForPayOrders = orderDao.getAllOrders(OrderStatus.WAITING_FOR_PAY)
    val allProcessingOrders = orderDao.getAllOrders(OrderStatus.PROCESSING)
    val allDeliveredOrders = orderDao.getAllOrders(OrderStatus.DELIVERED)
    val allCanceledOrders = orderDao.getAllOrders(OrderStatus.CANCELED)
    val allReturnedOrders = orderDao.getAllOrders(OrderStatus.RETURNED)

    suspend fun clearAllOrders(){
        orderDao.clearAllOrders()
    }

    suspend fun changeOrderStatus(newStatus: OrderStatus, orderId: String){
        orderDao.changeOrderStatus(newStatus, orderId)
    }

}