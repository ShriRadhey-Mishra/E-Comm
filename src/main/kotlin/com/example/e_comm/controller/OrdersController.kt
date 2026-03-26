package com.example.e_comm.controller

import com.example.e_comm.entity.Orders
import com.example.e_comm.service.OrdersService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/orders")
class OrdersController(val orderService: OrdersService) {

    // post controller - place order
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun placeOrder(@RequestBody order: Orders): Mono<Orders> {
        return orderService.placeOrder(order)
    }

    // get controller - get order by order id
    @GetMapping("/orderId/{oId}")
    fun getOrderByOrderId(@PathVariable("oId") oId: Long): Mono<Orders> {
        return orderService.getOrderByOrderId(oId)
    }

    // get controller - get order by customer id
    @GetMapping("/customerId/{cId}")
    fun getOrderByCustomerId(@PathVariable("cId") cId: Long): Flux<Orders> {
        return orderService.getOrderByCustomerId(cId)
    }

    // put controller - update order from the given id
    @PutMapping("/{oId}")
    fun updateOrder(@PathVariable("oId") oId: Long, @RequestBody newOrder: Orders): Mono<Orders> {
        return orderService.updateOrder(oId, newOrder)
    }

    // delete controller - cancel order
    @DeleteMapping("/{oId}")
    fun cancelOrder(@PathVariable("oId") oId: Long): Unit {
        orderService.cancelOrder(oId)
    }
}