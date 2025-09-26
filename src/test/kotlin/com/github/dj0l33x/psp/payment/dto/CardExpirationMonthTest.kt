package com.github.dj0l33x.psp.payment.dto

import com.github.dj0l33x.psp.payment.CardExpirationMonthException
import com.github.dj0l33x.psp.payment.dto.CardExpirationMonth
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class CardExpirationMonthTest {
    @Test
    fun `month is parsed successfully`() {
        val month = assertDoesNotThrow { CardExpirationMonth("01") }
        assertEquals("01", month.value)
    }

    @Test
    fun `month is parsed successfully snd should be trimmed`() {
        val month = assertDoesNotThrow { CardExpirationMonth("   12\t") }
        assertEquals("12", month.value)
    }

    @Test
    fun `parsing month failed - there are only 12 months`() {
        assertThrows<CardExpirationMonthException> { CardExpirationMonth("13") }
    }

    @Test
    fun `parsing month failed - month cannot be empty`() {
        assertThrows<CardExpirationMonthException> { CardExpirationMonth("") }
    }
}
