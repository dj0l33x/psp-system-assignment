package com.github.dj0l33x.psp.payment.db

import java.util.UUID

class Payment(
    val id: UUID = UUID.randomUUID(),
    val merchantId: UUID,
    val status: PaymentStatus,
)

enum class PaymentStatus {
    PENDING,
    APPROVED,
    DENIED,
    FAILED,
}
