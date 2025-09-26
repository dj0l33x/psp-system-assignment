package com.github.dj0l33x.psp.payment.dto

import com.github.dj0l33x.psp.payment.AmountException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class AmountTest {
    @Test
    fun `amount is parsed successfully with scale 2`() {
        assertDoesNotThrow { Amount("10.00") }
    }

    @Test
    fun `amount is parsed successfully with scale 1`() {
        assertDoesNotThrow { Amount("10.0") }
    }

    @Test
    fun `amount is parsed successfully`() {
        assertDoesNotThrow { Amount("10") }
    }

    @Test
    fun `amount is parsed successfully with whitespaces`() {
        assertDoesNotThrow { Amount("   10\t") }
    }

    @Test
    fun `amount is parsed successfully with 0 at the start`() {
        val amount = assertDoesNotThrow { Amount("010") }
        assert(amount.value.toBigDecimal() == 10.toBigDecimal())
    }

    @Test
    fun `parsing negative amount is failed`() {
        assertThrows<AmountException> { Amount("-10") }
    }

    @Test
    fun `parsing zero amount is failed`() {
        assertThrows<AmountException> { Amount("0") }
    }
}
