package com.github.dj0l33x.psp.payment

import com.github.dj0l33x.psp.acquirer.AcquirerService
import com.github.dj0l33x.psp.acquirer.AcquirerTransaction
import com.github.dj0l33x.psp.acquirer.AcquirerTransactionStatus
import com.github.dj0l33x.psp.createAcquirerTransactionResult
import com.github.dj0l33x.psp.createCard
import com.github.dj0l33x.psp.createPayment
import com.github.dj0l33x.psp.payment.db.Payment
import com.github.dj0l33x.psp.payment.db.PaymentRepository
import com.github.dj0l33x.psp.payment.db.PaymentStatus
import com.github.dj0l33x.psp.payment.dto.Amount
import com.github.dj0l33x.psp.payment.dto.Currency
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import java.util.UUID
import kotlin.test.assertEquals
import org.mockito.kotlin.any as mockAny

@ExtendWith(MockitoExtension::class)
class PaymentServiceTest {
    @InjectMocks
    private lateinit var paymentService: PaymentService

    @Mock
    private lateinit var repository: PaymentRepository

    @Mock
    private lateinit var acquirerService: AcquirerService

    private val merchantId = UUID.randomUUID()
    private val currency = Currency("USD")
    private val amount = Amount("100")
    private val card = createCard()

    @Test
    fun `data from acquirer transaction is mapped correctly to payment`() {
        val mockTransactionResult = createAcquirerTransactionResult(status = AcquirerTransactionStatus.APPROVED)
        whenever(acquirerService.processTransaction(mockAny<AcquirerTransaction>())).thenReturn(
            mockTransactionResult,
        )

        val mockPayment = createPayment()
        whenever(repository.save(mockAny<Payment>())).thenReturn(mockPayment)

        val result =
            paymentService.create(
                merchantId = merchantId,
                currency = currency,
                amount = amount,
                card = card,
            )

        assertNotNull(result)
        assertEquals(mockTransactionResult.acquirerTransactionId, result.acquirerTransactionId)
        assertEquals(mockTransactionResult.acquirerName, result.acquirerName)
        assertEquals(PaymentStatus.APPROVED, result.status)
    }

    @Test
    fun `denied status from acquirer transaction is mapped correctly to payment`() {
        val mockTransactionResult = createAcquirerTransactionResult(status = AcquirerTransactionStatus.DENIED)
        whenever(acquirerService.processTransaction(mockAny<AcquirerTransaction>())).thenReturn(
            mockTransactionResult,
        )

        val mockPayment = createPayment()
        whenever(repository.save(mockAny<Payment>())).thenReturn(mockPayment)

        val result =
            paymentService.create(
                merchantId = merchantId,
                currency = currency,
                amount = amount,
                card = card,
            )

        assertNotNull(result)
        assertEquals(PaymentStatus.DENIED, result.status)
    }

    @Test
    fun `failed status from acquirer transaction is mapped correctly to payment`() {
        val mockTransactionResult = createAcquirerTransactionResult(status = AcquirerTransactionStatus.FAILED)
        whenever(acquirerService.processTransaction(mockAny<AcquirerTransaction>())).thenReturn(
            mockTransactionResult,
        )

        val mockPayment = createPayment()
        whenever(repository.save(mockAny<Payment>())).thenReturn(mockPayment)

        val result =
            paymentService.create(
                merchantId = merchantId,
                currency = currency,
                amount = amount,
                card = card,
            )

        assertNotNull(result)
        assertEquals(PaymentStatus.FAILED, result.status)
    }
}
