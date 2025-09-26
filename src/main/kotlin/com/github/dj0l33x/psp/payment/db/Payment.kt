package com.github.dj0l33x.psp.payment.db

import java.util.UUID

data class Payment(
    val id: UUID = UUID.randomUUID(),
    val merchantId: UUID,
    val status: PaymentStatus,
    val acquirerTransactionId: UUID? = null,
    val acquirerName: String? = null,
)

enum class PaymentStatus {
    PENDING,
    APPROVED,
    DENIED,
    FAILED,
}
