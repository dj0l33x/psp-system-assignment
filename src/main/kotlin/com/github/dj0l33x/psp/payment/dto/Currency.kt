package com.github.dj0l33x.psp.payment.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.github.dj0l33x.psp.payment.CurrencyException

@JvmInline
value class Currency private constructor(
    val currencyCode: String,
) {
    companion object {
        private val CURRENCY_REGEX = Regex("^[A-Za-z]{3}$")

        @JsonCreator
        fun from(currencyCode: String): Currency = Currency(currencyCode.trim().uppercase())

        operator fun invoke(currencyCode: String): Currency = from(currencyCode)
    }

    init {
        validate(currencyCode)
    }

    private fun validate(currencyCode: String) {
        if (!CURRENCY_REGEX.matches(currencyCode)) {
            throw CurrencyException(currencyCode)
        }
    }
}
