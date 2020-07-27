package com.producer.employee

import org.springframework.stereotype.Service

@Service
class EmployeeService {

    fun findById(id: Int) : Employee? {
        return getList().find { it.id == id }
    }

   companion object {

       fun getList(): List<Employee> {
           val employeeList = mutableListOf<Employee>()
           employeeList.add(Employee(1, "Max","Payne", 54000.0,"M"))
           employeeList.add(Employee(2, "Bob","Martin", 73000.0,"M"))
           employeeList.add(Employee(3, "Amy","Flower", 64000.0,"F"))

           return employeeList
       }

   }
}