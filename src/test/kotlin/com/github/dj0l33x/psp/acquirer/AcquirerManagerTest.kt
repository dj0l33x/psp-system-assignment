package com.github.dj0l33x.psp.acquirer

import com.github.dj0l33x.psp.createAcquirerTransaction
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AcquirerManagerTest {
    private val manager =
        AcquirerManager(
            listOf(
                EvenAcquirer(FakeTransactionProcessor()),
                OddAcquirer(FakeTransactionProcessor()),
            ),
        )

    @Test
    fun `even acquirer is selected`() {
        val transaction = createAcquirerTransaction(cardNumber = "1000010000000000")
        assertTrue(manager.getSuitableAcquirer(transaction) is EvenAcquirer)
    }

    @Test
    fun `odd acquirer is selected`() {
        val transaction = createAcquirerTransaction(cardNumber = "1010010000000000")
        assertTrue(manager.getSuitableAcquirer(transaction) is OddAcquirer)
    }
}
