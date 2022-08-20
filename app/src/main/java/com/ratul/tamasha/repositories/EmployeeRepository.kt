package com.ratul.tamasha.repositories

import android.util.Log
import com.ratul.tamasha.data.Employee
import com.ratul.tamasha.data.EmployeeService
import com.ratul.tamasha.utils.SafeApiRequest
import javax.inject.Inject

class EmployeeRepository @Inject constructor(private val employeeService: EmployeeService) : SafeApiRequest(){

    suspend fun getEmployees(): List<Employee> {
        val response =
            apiRequest { employeeService.getEmployees() }
        return response.data.toList()
    }

}