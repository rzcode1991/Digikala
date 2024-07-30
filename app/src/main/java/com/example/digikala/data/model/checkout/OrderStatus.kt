package com.example.digikala.data.model.checkout

enum class OrderStatus {
    NOT_PAID_YET,
    WAITING_FOR_PAY,
    PROCESSING,
    DELIVERED,
    CANCELED,
    RETURNED
}