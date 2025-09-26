package com.github.dj0l33x.psp

import com.github.dj0l33x.psp.payment.dto.Amount
import com.github.dj0l33x.psp.payment.dto.Card
import com.github.dj0l33x.psp.payment.dto.CardCvv
import com.github.dj0l33x.psp.payment.dto.CardExpirationDate
import com.github.dj0l33x.psp.payment.dto.CardExpirationMonth
import com.github.dj0l33x.psp.payment.dto.CardExpirationYear
import com.github.dj0l33x.psp.payment.dto.CardNumber
import com.github.dj0l33x.psp.payment.dto.CreatePaymentDto
import com.github.dj0l33x.psp.payment.dto.Currency
import java.util.UUID

fun createPaymentDto(
    merchantId: UUID = UUID.randomUUID(),
    currencyCode: String = "USD",
    amount: String = "10.00",
    cardNumber: String = "4438231558582693",
    cvv: String = "123",
    expirationMonth: String = "10",
    expirationYear: String = "2028",
): CreatePaymentDto =
    CreatePaymentDto(
        merchantId = merchantId,
        currency = Currency(currencyCode),
        amount = Amount(amount),
        card =
            Card(
                number = CardNumber(cardNumber),
                cvv = CardCvv(cvv),
                expiration =
                    CardExpirationDate(
                        month = CardExpirationMonth(expirationMonth),
                        year = CardExpirationYear(expirationYear),
                    ),
            ),
    )
