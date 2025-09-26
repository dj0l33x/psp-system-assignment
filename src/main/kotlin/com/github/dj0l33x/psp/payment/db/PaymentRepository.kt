package com.github.dj0l33x.psp.payment.db

import org.springframework.stereotype.Repository
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Repository
class PaymentRepository {
    private val payments = ConcurrentHashMap<UUID, Payment>()

    fun save(payment: Payment): Payment {
        payments[payment.id] = payment
        return payment
    }

    fun findById(id: UUID): Payment? = payments[id]
}
