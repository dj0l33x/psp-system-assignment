package com.github.dj0l33x.psp

import com.github.dj0l33x.psp.acquirer.AcquirerTransaction
import com.github.dj0l33x.psp.acquirer.AcquirerTransactionCardNumber
import com.github.dj0l33x.psp.payment.dto.Amount
import com.github.dj0l33x.psp.payment.dto.Card
import com.github.dj0l33x.psp.payment.dto.CardCvv
import com.github.dj0l33x.psp.payment.dto.CardExpirationDate
import com.github.dj0l33x.psp.payment.dto.CardExpirationMonth
import com.github.dj0l33x.psp.payment.dto.CardExpirationYear
import com.github.dj0l33x.psp.payment.dto.CardNumber
import com.github.dj0l33x.psp.payment.dto.CreatePaymentDto
import com.github.dj0l33x.psp.payment.dto.Currency
import java.time.LocalDate
import java.util.UUID

fun createPaymentDto(
    merchantId: UUID = UUID.randomUUID(),
    currencyCode: String = "USD",
    amount: String = "10.00",
    card: Card = createCard(),
): CreatePaymentDto =
    CreatePaymentDto(
        merchantId = merchantId,
        currency = Currency(currencyCode),
        amount = Amount(amount),
        card = card,
    )

fun createCard(
    cardNumber: String = "4438231558582693",
    cvv: String = "123",
    expirationMonth: String = LocalDate.now().monthValue.toString(),
    expirationYear: String = LocalDate.now().year.toString(),
): Card =
    Card(
        number = CardNumber(cardNumber),
        cvv = CardCvv(cvv),
        expiration =
            CardExpirationDate(
                month = CardExpirationMonth(expirationMonth),
                year = CardExpirationYear(expirationYear),
            ),
    )

fun createAcquirerTransaction(cardNumber: String = "4438231558582693"): AcquirerTransaction =
    AcquirerTransaction(cardNumber = AcquirerTransactionCardNumber(number = cardNumber))
