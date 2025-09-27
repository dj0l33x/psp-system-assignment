package com.github.dj0l33x.psp.acquirer

import java.math.BigDecimal
import java.util.UUID

data class AcquirerTransaction(
    val amount: BigDecimal,
    val currency: String,
    val cardNumber: AcquirerTransactionCardNumber,
)

data class AcquirerTransactionCardNumber(
    val number: String,
) {
    private companion object {
        const val BIN_LENGTH = 6
    }

    fun extractBin(): String = this.number.take(BIN_LENGTH)
}

data class AcquirerTransactionResult(
    val acquirerTransactionId: UUID? = null,
    val acquirerName: String? = null,
    val status: AcquirerTransactionStatus,
)

enum class AcquirerTransactionStatus {
    APPROVED,
    DENIED,
    FAILED,
}
