package com.fecred.idfor.simplecrm

import com.fecred.idfor.simplecrm.employee.EmployeeService
import org.bson.Document
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class EmployeeTests {

    @Autowired
    lateinit var employeeService: EmployeeService

    @Test
    fun countTest() {
        println(employeeService.page(0, 1).first()!!.getString("name"))
    }
}