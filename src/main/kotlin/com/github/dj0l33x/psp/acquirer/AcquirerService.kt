package com.github.dj0l33x.psp.acquirer

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AcquirerService(
    private val manager: AcquirerManager,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun processTransaction(transaction: AcquirerTransaction): AcquirerTransactionResult =
        try {
            manager
                .getSuitableAcquirer(transaction)
                .processTransaction(transaction)
                .also {
                    log.info(
                        "Transaction ${it.acquirerTransactionId} has been processed, result: ${it.status}",
                    )
                }
        } catch (e: Exception) {
            log.error(
                "Error processing transaction for card ${transaction.cardNumber.number}",
                e,
            )
            AcquirerTransactionResult(
                status = AcquirerTransactionStatus.FAILED,
            )
        }
}
