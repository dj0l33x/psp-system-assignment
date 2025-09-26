package com.github.dj0l33x.psp.payment.dto

import java.util.UUID

data class CreatePaymentDto(
    val merchantId: UUID,
    val currency: Currency,
    val amount: Amount,
    val card: Card,
)
