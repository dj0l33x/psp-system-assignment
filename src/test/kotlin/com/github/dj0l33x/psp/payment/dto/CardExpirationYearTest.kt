package com.github.dj0l33x.psp.payment.dto

import com.github.dj0l33x.psp.payment.CardExpirationYearException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardExpirationYearTest {
    @Test
    fun `year is parsed successfully`() {
        val year = assertDoesNotThrow { CardExpirationYear("2025") }
        assertEquals("2025", year.value)
    }

    @Test
    fun `year is parsed successfully snd should be trimmed`() {
        val year = assertDoesNotThrow { CardExpirationYear("   2000\t") }
        assertEquals("2000", year.value)
    }

    @Test
    fun `parsing year failed - cannot be empty`() {
        assertThrows<CardExpirationYearException> { CardExpirationYear("") }
    }

    @Test
    fun `parsing year failed - failing all years before 2000`() {
        assertThrows<CardExpirationYearException> { CardExpirationYear("1999") }
    }
}
