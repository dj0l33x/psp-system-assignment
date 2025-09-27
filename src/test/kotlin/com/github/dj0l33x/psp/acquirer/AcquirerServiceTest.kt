package com.github.dj0l33x.psp.acquirer

import com.github.dj0l33x.psp.createAcquirerTransaction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AcquirerServiceTest {
    @InjectMocks
    lateinit var service: AcquirerService

    @Mock
    lateinit var mockManager: AcquirerManager

    @Mock
    lateinit var mockAcquirer: Acquirer

    private val testTransaction = createAcquirerTransaction()

    @BeforeEach
    fun setUp() {
        `when`(mockManager.getSuitableAcquirer(testTransaction)).thenReturn(mockAcquirer)
    }

    @Test
    fun `service returns expected status value`() {
        val expectedStatus = AcquirerTransactionStatus.APPROVED
        `when`(mockAcquirer.processTransaction(testTransaction)).thenReturn(
            AcquirerTransactionResult(
                status = expectedStatus,
            ),
        )

        val result = service.processTransaction(testTransaction)
        assertNotNull(result)
        assertEquals(expectedStatus, result.status)
    }

    @Test
    fun `service returns failed status value if error happened`() {
        `when`(mockAcquirer.processTransaction(testTransaction)).thenThrow(RuntimeException("Test exception"))

        val result = service.processTransaction(testTransaction)
        assertNotNull(result)
        assertEquals(AcquirerTransactionStatus.FAILED, result.status)
    }
}
