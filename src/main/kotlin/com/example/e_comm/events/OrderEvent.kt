package com.example.e_comm.events

import com.example.e_comm.entity.Orders

data class OrderEvent(
    val eventId: String,
    val eventType: String,
    val order: Orders?
)