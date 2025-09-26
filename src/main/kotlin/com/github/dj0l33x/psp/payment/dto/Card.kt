package com.github.dj0l33x.psp.payment.dto

import com.github.dj0l33x.psp.payment.CardExpiredException
import java.time.LocalDate

data class Card(
    val number: CardNumber,
    val expiration: CardExpirationDate,
    val cvv: CardCvv,
) {
    init {
        isCardExpiredAlready()
    }

    private fun isCardExpiredAlready() {
        val localDate = LocalDate.now()
        val currentYear = localDate.year
        val currentMonth = localDate.monthValue

        val expirationMonth = expiration.month.value
        val expirationYear = expiration.year.value
        if (expirationYear.toInt() < currentYear || (expirationYear.toInt() == currentYear && expirationMonth.toInt() < currentMonth)) {
            throw CardExpiredException(expirationMonth, expirationYear)
        }
    }
}
