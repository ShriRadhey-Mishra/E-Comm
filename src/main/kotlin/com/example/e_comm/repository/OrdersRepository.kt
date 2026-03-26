package com.example.e_comm.repository

import com.example.e_comm.entity.Orders
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface OrdersRepository: ReactiveCrudRepository<Orders, Long> {

    fun findOrderByCustomerId(cId: Long): Flux<Orders>
}