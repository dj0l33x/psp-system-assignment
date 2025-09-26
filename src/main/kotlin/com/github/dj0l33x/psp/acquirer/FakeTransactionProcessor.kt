package com.github.dj0l33x.psp.acquirer

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class FakeTransactionProcessor {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun process(transaction: AcquirerTransaction): AcquirerTransactionResult {
        val isEven =
            transaction.cardNumber.number
                .last()
                .digitToInt() % 2 == 0
        val status =
            if (isEven) {
                log.info("Transaction for card ${transaction.cardNumber.number} has been approved")
                AcquirerTransactionStatus.APPROVED
            } else {
                log.warn("Transaction for card ${transaction.cardNumber.number} has been rejected")
                AcquirerTransactionStatus.DENIED
            }
        return AcquirerTransactionResult(
            acquirerTransactionId = UUID.randomUUID(),
            status = status,
        )
    }
}
