package com.github.dj0l33x.psp.payment.dto

import java.util.UUID

data class PaymentDto(
    val id: UUID,
    val merchantId: UUID,
    val status: String,
)
