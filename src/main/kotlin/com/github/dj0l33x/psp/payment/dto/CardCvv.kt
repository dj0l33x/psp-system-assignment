package com.github.dj0l33x.psp.payment.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.github.dj0l33x.psp.payment.CardCvvException

@JvmInline
value class CardCvv private constructor(
    val value: String,
) {
    companion object {
        private val CVV_REGEX = Regex("^[0-9]{3}$")

        @JsonCreator
        fun from(value: String): CardCvv = CardCvv(value.trim())

        operator fun invoke(value: String): CardCvv = from(value)
    }

    init {
        validate(value)
    }

    private fun validate(value: String) {
        if (!CVV_REGEX.matches(value)) {
            throw CardCvvException(value)
        }
    }
}
