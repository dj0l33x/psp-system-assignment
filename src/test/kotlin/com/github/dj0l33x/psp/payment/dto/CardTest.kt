package com.github.dj0l33x.psp.payment.dto

import com.github.dj0l33x.psp.createCard
import com.github.dj0l33x.psp.payment.CardExpiredException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class CardTest {
    @Test
    fun `card init failed - expiration year in past`() {
        val expiredYear = LocalDate.now().minusYears(1).year
        assertThrows<CardExpiredException> { createCard(expirationYear = expiredYear.toString()) }
    }

    @Test
    fun `card init failed - expiration month in past`() {
        val expiredMonth = LocalDate.now().minusMonths(1).monthValue
        assertThrows<CardExpiredException> { createCard(expirationMonth = expiredMonth.toString()) }
    }

    @Test
    fun `card init successful for current month and year`() {
        assertDoesNotThrow { createCard() }
    }

    @Test
    fun `card init successful for month and year in future`() {
        val month = LocalDate.now().plusMonths(1).monthValue
        val year = LocalDate.now().plusYears(1).year
        assertDoesNotThrow { createCard(expirationMonth = month.toString(), expirationYear = year.toString()) }
    }
}
