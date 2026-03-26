package com.example.e_comm.entity

import com.example.e_comm.enums.OrderStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "orders")
data class Orders(
    @Id
    val orderId: Long,
    val orderName: String,
    val customerId: Long,
    val amount: Double,
    val orderStatus: OrderStatus
)