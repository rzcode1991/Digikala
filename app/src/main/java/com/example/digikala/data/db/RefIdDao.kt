package com.example.digikala.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.zarinpal.RefId
import kotlinx.coroutines.flow.Flow

@Dao
interface RefIdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRefId(refId: RefId)

    @Query("SELECT refId FROM ref_ids WHERE orderId =:orderId")
    suspend fun getRefIdByOrderId(orderId: String): Int?

}