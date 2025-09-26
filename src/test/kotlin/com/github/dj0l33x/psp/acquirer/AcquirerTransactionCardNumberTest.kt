package com.github.dj0l33x.psp.acquirer

import com.github.dj0l33x.psp.createAcquirerTransaction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AcquirerTransactionCardNumberTest {
    @Test
    fun `extract bin correctly`() {
        val cardNumber = createAcquirerTransaction(cardNumber = "1234567890000000").cardNumber
        assertEquals("123456", cardNumber.extractBin())
    }
}
