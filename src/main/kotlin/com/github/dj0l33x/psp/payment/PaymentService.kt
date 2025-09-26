package com.github.dj0l33x.psp.payment

import com.github.dj0l33x.psp.payment.db.Payment
import com.github.dj0l33x.psp.payment.db.PaymentRepository
import com.github.dj0l33x.psp.payment.db.PaymentStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PaymentService(
    private val repository: PaymentRepository,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun create(merchantId: UUID): Payment {
        val payment = Payment(merchantId = merchantId, status = PaymentStatus.APPROVED)
        return repository
            .save(payment)
            .also { log.info("Payment ${it.id} has been created") }
    }
}