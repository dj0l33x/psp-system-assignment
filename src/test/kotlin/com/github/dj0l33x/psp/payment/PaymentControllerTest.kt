package com.github.dj0l33x.psp.payment

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.dj0l33x.psp.createPaymentDto
import com.github.dj0l33x.psp.payment.db.PaymentRepository
import com.github.dj0l33x.psp.payment.db.PaymentStatus
import com.github.dj0l33x.psp.payment.dto.CreatePaymentDto
import com.github.dj0l33x.psp.payment.dto.PaymentDto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var repository: PaymentRepository

    @Test
    fun `payment is created`() {
        val merchantId = UUID.randomUUID()
        val createPaymentDto = createPaymentDto(merchantId = merchantId)

        val paymentDto =
            mockMvc
                .perform(
                    post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(createPaymentDto.toJson()),
                ).andExpect(status().isCreated)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty)
                .andExpect(jsonPath("$.merchantId").value(merchantId.toString()))
                .andExpect(jsonPath("$.status").value(PaymentStatus.DENIED.name))
                .andReturn()
                .response
                .contentAsString
                .toPaymentDto()

        val payment = repository.findById(paymentDto.id)
        assertNotNull(payment)
        assertEquals(merchantId, payment.merchantId)
    }

    private fun CreatePaymentDto.toJson() = objectMapper.writeValueAsString(this)

    private fun String.toPaymentDto() = objectMapper.readValue(this, PaymentDto::class.java)
}
