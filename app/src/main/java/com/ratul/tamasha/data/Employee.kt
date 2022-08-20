package com.ratul.tamasha.data

data class EmployeeResponse(

    val status : String,
    val data : Array<Employee>

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EmployeeResponse

        if (status != other.status) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}

data class Employee(
    val id : Int,
    val employee_name : String,
    val employee_salary : Int,
    val employee_age : Int,
    val profile_image : String,
)