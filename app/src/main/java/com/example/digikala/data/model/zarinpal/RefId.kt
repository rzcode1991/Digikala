package com.example.digikala.data.model.zarinpal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikala.utils.Constants.REF_ID_TABLE

@Entity(tableName = REF_ID_TABLE)
data class RefId(
    @PrimaryKey
    val orderId: String,
    val refId: Int
)
