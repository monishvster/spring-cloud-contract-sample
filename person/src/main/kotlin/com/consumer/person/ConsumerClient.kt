package com.consumer.person

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ConsumerClient {

    fun getPerson(id: Int): Person? {
        val restTemplate = RestTemplate()

        val result = restTemplate.exchange("http://localhost:8081/employee/$id",
                HttpMethod.GET, null, Person::class.java)

        return result.body
    }
}