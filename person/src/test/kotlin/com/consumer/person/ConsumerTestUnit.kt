package com.consumer.person

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.client.WireMock
import org.apache.http.HttpHeaders
import org.assertj.core.api.BDDAssertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
@AutoConfigureWireMock(port = 8081)
@AutoConfigureJson
class ConsumerTestUnit {

    @Autowired
    private lateinit var consumerClient: ConsumerClient

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `get person success`() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/employee/1")).willReturn(
                WireMock.aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(personJson(Person(1,"Jane", "Doe")))
        ))

        BDDAssertions.then(this.consumerClient.getPerson(1)?.fname).isEqualTo("Jane")
    }

    private fun personJson(person: Person): String {
        return objectMapper.writeValueAsString(person)
    }
}