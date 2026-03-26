package com.example.e_comm.service

import com.example.e_comm.entity.Orders
import com.example.e_comm.events.OrderEvent
import com.example.e_comm.repository.OrdersRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class OrdersService(val ordersRepository: OrdersRepository, val orderEventProducer: OrderEventProducer) {
    private val log = LoggerFactory.getLogger(OrdersService::class.java)

    // Place Order
    fun placeOrder(orders: Orders): Mono<Orders> {
        return ordersRepository.save(orders)
            .doOnSuccess { savedOrder ->
                val event = OrderEvent(
                    eventId = java.util.UUID.randomUUID().toString(),
                    eventType = "ORDER_CREATED",
                    order = savedOrder
                )
                orderEventProducer.sendEvent(savedOrder?.orderId.toString(), event)

                log.info("Order has been placed.")
            }

    }

    // Get Order by OrderId
    fun getOrderByOrderId(oId: Long): Mono<Orders> {
        log.info("Here is your order with order id: {}", oId)
        return ordersRepository.findById(oId)
    }

    // Get Order by CustomerId
    fun getOrderByCustomerId(cId: Long): Flux<Orders> {
        log.info("Here is your order with customer id: {}", cId)
        return ordersRepository.findOrderByCustomerId(cId);
    }

    // Update Order
    fun updateOrder(oId: Long, updatedOrder: Orders): Mono<Orders> {
        log.info("Your order has been updated: {}", oId)
        val ordersMono: Mono<Orders> = getOrderByOrderId(oId)
        return ordersMono.flatMap{
            order -> val updated = order.copy(
                orderName = updatedOrder.orderName,
                customerId = updatedOrder.customerId,
                amount = updatedOrder.amount,
                orderStatus = updatedOrder.orderStatus
            )
            ordersRepository.save(updated)
        }
    }

    // Cancel Order
    fun cancelOrder(oId: Long): Mono<Void> {
        log.info("Your order with order id: {}, has been cancelled.", oId)
        return ordersRepository.deleteById(oId)
    }
}