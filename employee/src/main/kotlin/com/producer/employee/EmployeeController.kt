package com.producer.employee

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeController(@Autowired val employeeService: EmployeeService) {

    @GetMapping(value = ["employee/{id}"])
    fun getEmployee(@PathVariable("id") id: Int): ResponseEntity<Employee> {
        val employee = employeeService.findById(id)

        return if(employee!=null) {
            ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(employee)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }


}