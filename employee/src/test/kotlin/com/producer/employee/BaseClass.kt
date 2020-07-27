package com.producer.employee

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest(classes = [EmployeeApplication::class])
@RunWith(SpringRunner::class)
@AutoConfigureMessageVerifier
class BaseClass {

    @Autowired
    private lateinit var employeeController: EmployeeController

    @MockBean
    private lateinit var employeeService: EmployeeService

    @Before
    fun before() {
        val employee = Employee(1,"Jane", "Doe", 123000.00, "M")
        Mockito.`when`(employeeService.findById(1)).thenReturn(employee)

        RestAssuredMockMvc.standaloneSetup(employeeController)
    }
}