package com.github.dj0l33x.psp.acquirer

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AcquirerManager(
    private val acquirers: List<Acquirer>,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun getSuitableAcquirer(transaction: AcquirerTransaction): Acquirer {
        val isEven = isEven(transaction.cardNumber.extractBin())
        return acquirers
            .first { it.isEven() == isEven }
            .also { log.debug("Acquirer ${it::class.simpleName} will be used") }
    }

    private fun isEven(bin: String): Boolean = bin.filter { it.isDigit() }.sumOf { it.digitToInt() } % 2 == 0
}
