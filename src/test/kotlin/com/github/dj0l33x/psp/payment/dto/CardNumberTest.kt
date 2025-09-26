package com.github.dj0l33x.psp.payment.dto

import com.github.dj0l33x.psp.payment.CardNumberException
import com.github.dj0l33x.psp.payment.dto.CardNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class CardNumberTest {
    @Test
    fun `number is parsed successfully`() {
        val number = assertDoesNotThrow { CardNumber("4438231558582693") }
        assertEquals("4438231558582693", number.number)
    }

    @Test
    fun `parsing number is failed - length should be 16`() {
        assertThrows<CardNumberException> { CardNumber("443823155858269") }
    }

    @Test
    fun `parsing number is failed by luhn algorithm`() {
        assertThrows<CardNumberException> { CardNumber("4438231558582692") }
    }
}
