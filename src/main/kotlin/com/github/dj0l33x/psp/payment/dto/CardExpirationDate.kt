package com.github.dj0l33x.psp.payment.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.github.dj0l33x.psp.payment.CardExpirationMonthException
import com.github.dj0l33x.psp.payment.CardExpirationYearException

data class CardExpirationDate(
    val month: CardExpirationMonth,
    val year: CardExpirationYear,
)

@JvmInline
value class CardExpirationMonth private constructor(
    val value: String,
) {
    companion object {
        private val EXPIRATION_MONTH_REGEX = Regex("^(0[1-9]|1[0-2])$")

        @JsonCreator
        fun from(month: String): CardExpirationMonth {
            val formattedMonth =
                month
                    .trim()
                    .let { if (it.length == 1) "0$it" else it }
            return CardExpirationMonth(formattedMonth)
        }

        operator fun invoke(month: String): CardExpirationMonth = from(month)
    }

    init {
        validate(value)
    }

    private fun validate(value: String) {
        if (!EXPIRATION_MONTH_REGEX.matches(value)) {
            throw CardExpirationMonthException(value)
        }
    }
}

@JvmInline
value class CardExpirationYear private constructor(
    val value: String,
) {
    companion object {
        private val EXPIRATION_YEAR_REGEX = Regex("^(20[0-9]{2})$")

        @JsonCreator
        fun from(value: String): CardExpirationYear = CardExpirationYear(value.trim())

        operator fun invoke(value: String): CardExpirationYear = from(value)
    }

    init {
        validate(value)
    }

    private fun validate(value: String) {
        if (!EXPIRATION_YEAR_REGEX.matches(value)) {
            throw CardExpirationYearException(value)
        }
    }
}
