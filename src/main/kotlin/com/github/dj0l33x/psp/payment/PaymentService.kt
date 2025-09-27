package com.github.dj0l33x.psp.payment

import com.github.dj0l33x.psp.acquirer.AcquirerService
import com.github.dj0l33x.psp.acquirer.AcquirerTransaction
import com.github.dj0l33x.psp.acquirer.AcquirerTransactionCardNumber
import com.github.dj0l33x.psp.acquirer.AcquirerTransactionResult
import com.github.dj0l33x.psp.acquirer.AcquirerTransactionStatus
import com.github.dj0l33x.psp.payment.db.Payment
import com.github.dj0l33x.psp.payment.db.PaymentRepository
import com.github.dj0l33x.psp.payment.db.PaymentStatus
import com.github.dj0l33x.psp.payment.dto.Amount
import com.github.dj0l33x.psp.payment.dto.Card
import com.github.dj0l33x.psp.payment.dto.Currency
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class PaymentService(
    private val repository: PaymentRepository,
    private val acquirerService: AcquirerService,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun create(
        merchantId: UUID,
        currency: Currency,
        amount: Amount,
        card: Card,
    ): Payment {
        val payment = Payment(merchantId = merchantId, status = PaymentStatus.PENDING)
        repository
            .save(payment)
            .also { log.debug("Payment {} has been created in {} state", it.id, it.status) }

        val transactionResult = sendTransactionToAcquirer(amount, currency, card)

        return repository.save(
            payment.copy(
                status = resolveNewPaymentStatus(transactionResult),
                acquirerTransactionId = transactionResult.acquirerTransactionId,
                acquirerName = transactionResult.acquirerName,
            ),
        )
    }

    private fun sendTransactionToAcquirer(
        amount: Amount,
        currency: Currency,
        card: Card,
    ): AcquirerTransactionResult {
        val transaction =
            AcquirerTransaction(
                amount = BigDecimal(amount.value),
                currency = currency.currencyCode,
                cardNumber = AcquirerTransactionCardNumber(card.number.number),
            )
        return acquirerService.processTransaction(transaction)
    }

    private fun resolveNewPaymentStatus(transactionResult: AcquirerTransactionResult): PaymentStatus =
        when (transactionResult.status) {
            AcquirerTransactionStatus.APPROVED -> PaymentStatus.APPROVED
            AcquirerTransactionStatus.DENIED -> PaymentStatus.DENIED
            AcquirerTransactionStatus.FAILED -> PaymentStatus.FAILED
        }
}
