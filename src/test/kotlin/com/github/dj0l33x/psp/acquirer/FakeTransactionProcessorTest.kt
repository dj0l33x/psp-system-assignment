package com.github.dj0l33x.psp.acquirer

import com.github.dj0l33x.psp.createAcquirerTransaction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull

class FakeTransactionProcessorTest {
    private val processor = FakeTransactionProcessor()

    @Test
    fun `approve transaction`() {
        val transaction = createAcquirerTransaction(cardNumber = "0000000000000002")
        val result = processor.process(transaction)
        assertNotNull(result.acquirerTransactionId)
        assertEquals(AcquirerTransactionStatus.APPROVED, result.status)
    }

    @Test
    fun `deny transaction`() {
        val transaction = createAcquirerTransaction(cardNumber = "0000000000000001")
        val result = processor.process(transaction)
        assertNotNull(result.acquirerTransactionId)
        assertEquals(AcquirerTransactionStatus.DENIED, result.status)
    }
}
