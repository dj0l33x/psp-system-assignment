package com.github.dj0l33x.psp.payment

import com.github.dj0l33x.psp.payment.db.Payment
import com.github.dj0l33x.psp.payment.dto.CreatePaymentDto
import com.github.dj0l33x.psp.payment.dto.PaymentDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments", consumes = ["application/json"], produces = ["application/json"])
class PaymentController(
    private val service: PaymentService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody payment: CreatePaymentDto,
    ): PaymentDto =
        service
            .create(payment.merchantId, payment.currency, payment.amount, payment.card)
            .toDto()
}

private fun Payment.toDto() =
    PaymentDto(
        id = this.id,
        merchantId = this.merchantId,
        status = this.status.name,
    )
