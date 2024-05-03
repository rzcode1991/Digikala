package com.example.digikala.data.model.zarinpal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ref_ids")
data class RefId(
    @PrimaryKey
    val orderId: String,
    val refId: Int
)
