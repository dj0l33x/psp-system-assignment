package com.github.dj0l33x.psp.payment.dto

import com.github.dj0l33x.psp.payment.CurrencyException
import com.github.dj0l33x.psp.payment.dto.Currency
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class CurrencyTest {
    @Test
    fun `currency is successfully parsed`() {
        val currency = assertDoesNotThrow { Currency("USD") }
        assertEquals("USD", currency.currencyCode)
    }

    @Test
    fun `currency is successfully parsed for lower case`() {
        assertDoesNotThrow { Currency("usd") }
    }

    @Test
    fun `currency is successfully parsed with spaces`() {
        assertDoesNotThrow { Currency("  usd\t") }
    }

    @Test
    fun `currency parsing failed - only letters expected`() {
        assertThrows<CurrencyException> { Currency("US9") }
    }

    @Test
    fun `currency parsing failed - only 3 letters expected`() {
        assertThrows<CurrencyException> { Currency("USDT") }
    }
}
