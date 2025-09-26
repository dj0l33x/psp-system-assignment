package com.github.dj0l33x.psp.payment.dto

data class Card(
    val number: CardNumber,
    val expiration: CardExpirationDate,
    val cvv: CardCvv,
)
