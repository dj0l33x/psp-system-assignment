package com.github.dj0l33x.psp.payment.dto

import com.github.dj0l33x.psp.payment.CardCvvException
import com.github.dj0l33x.psp.payment.dto.CardCvv
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class CardCvvTest {
    @Test
    fun `cvv is parsed successfully`() {
        val cvv = assertDoesNotThrow { CardCvv("970") }
        assertEquals("970", cvv.value)
    }

    @Test
    fun `cvv is parsed successfully snd should be trimmed`() {
        val cvv = assertDoesNotThrow { CardCvv("   970\t") }
        assertEquals("970", cvv.value)
    }

    @Test
    fun `parsing cvv failed - only numbers expected`() {
        assertThrows<CardCvvException> { CardCvv("97a") }
    }

    @Test
    fun `parsing cvv failed - 3 numbers expected`() {
        assertThrows<CardCvvException> { CardCvv("97") }
    }
}
