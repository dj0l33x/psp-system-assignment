package com.github.dj0l33x.psp.payment.db

import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PaymentRepository {
    private val payments = mutableMapOf<UUID, Payment>()

    fun save(payment: Payment): Payment {
        payments[payment.id] = payment
        return payment
    }

    fun findById(id: UUID): Payment? = payments[id]
}
