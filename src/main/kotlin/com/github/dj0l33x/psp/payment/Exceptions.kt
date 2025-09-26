package com.github.dj0l33x.psp.payment

class CurrencyException(
    currencyCode: String,
) : Exception("Invalid currency code: $currencyCode")

class AmountException(
    amount: String,
) : Exception("Invalid amount value: $amount")

class CardCvvException(
    cvv: String,
) : Exception("Invalid cvv value: $cvv")

class CardExpirationMonthException(
    month: String,
) : Exception("Invalid month: $month. Must be between 01 and 12.")

class CardExpirationYearException(
    year: String,
) : Exception("Invalid expiration year value: $year")

class CardNumberException(
    year: String,
) : Exception("Invalid card number value: $year")

class CardExpiredException(
    month: String,
    year: String,
) : Exception("Card is expired: $month/$year")
