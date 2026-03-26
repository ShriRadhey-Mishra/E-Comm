package com.example.e_comm.service

import com.example.e_comm.events.OrderEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class OrderEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, OrderEvent>
) {
    private val topic = "order-events"

    fun sendEvent(orderId: String, event: OrderEvent) {
        kafkaTemplate.send(topic, orderId, event)
    }
}