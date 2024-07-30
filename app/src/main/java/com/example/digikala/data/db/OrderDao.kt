package com.example.digikala.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.checkout.Order
import com.example.digikala.data.model.checkout.OrderStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewOrder(order: Order)

    @Query("select * from order_item_table where orderStatus=:status")
    fun getAllOrders(status: OrderStatus): Flow<List<Order>>

    @Query("delete from order_item_table")
    suspend fun clearAllOrders()

    @Query("update order_item_table set orderStatus=:newStatus where orderId=:orderId")
    suspend fun changeOrderStatus(newStatus: OrderStatus, orderId: String)

}