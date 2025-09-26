package com.github.dj0l33x.psp.payment.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.github.dj0l33x.psp.payment.AmountException
import java.math.BigDecimal

@JvmInline
value class Amount private constructor(
    val value: String,
) {
    companion object {
        private val AMOUNT_REGEX = Regex("^[0-9]+(\\.[0-9]{1,2})?$")

        @JsonCreator
        fun from(value: String): Amount = Amount(value.trim())

        operator fun invoke(value: String) = from(value)
    }

    init {
        validate(value)
    }

    private fun validate(value: String) {
        if (!AMOUNT_REGEX.matches(value)) {
            throw AmountException(value)
        }
        val amountValue = BigDecimal(value)
        if (amountValue <= BigDecimal.ZERO) {
            throw AmountException(value)
        }
    }
}
