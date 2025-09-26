package com.github.dj0l33x.psp.acquirer

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class OddAcquirer(
    private val processor: FakeTransactionProcessor,
) : Acquirer {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun isEven(): Boolean = false

    override fun processTransaction(transaction: AcquirerTransaction): AcquirerTransactionResult =
        processor
            .process(transaction)
            .copy(acquirerName = "OddAcquirer")
}
