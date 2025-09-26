package com.github.dj0l33x.psp.payment.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.github.dj0l33x.psp.payment.CardNumberException

@JvmInline
value class CardNumber private constructor(
    val number: String,
) {
    companion object {
        private val CARD_NUMBER_REGEX = Regex("^[0-9]{16}$")

        @JsonCreator
        fun from(number: String): CardNumber = CardNumber(number.trim())

        operator fun invoke(number: String): CardNumber = from(number)
    }

    init {
        validate(number)
    }

    private fun validate(value: String) {
        if (!CARD_NUMBER_REGEX.matches(value) || !luhnCheck(value)) {
            throw CardNumberException(value)
        }
    }

    private fun luhnCheck(number: String): Boolean {
        var sum = 0
        var alternate = false
        for (i in number.length - 1 downTo 0) {
            var n = number.substring(i, i + 1).toInt()
            if (alternate) {
                n *= 2
                if (n > 9) {
                    n = (n % 10) + 1
                }
            }
            sum += n
            alternate = !alternate
        }
        return (sum % 10 == 0)
    }
}
