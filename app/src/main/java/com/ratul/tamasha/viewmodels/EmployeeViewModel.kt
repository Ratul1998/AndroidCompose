package com.ratul.tamasha.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ratul.tamasha.data.Employee
import com.ratul.tamasha.repositories.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(private val employeeRepository: EmployeeRepository) : ViewModel(){

    val employeeState = MutableStateFlow<EmployeeState>(EmployeeState.Uninitialized)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

        employeeState.tryEmit(EmployeeState.ErrorState(message = throwable.message.toString()))

    }

    fun getEmployees(){

        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {

            employeeState.tryEmit(EmployeeState.LoadingState)

            employeeState.tryEmit(EmployeeState.DataFetched(employeeRepository.getEmployees()))

        }

    }

}

sealed class EmployeeState {

    object Uninitialized : EmployeeState()
    object LoadingState : EmployeeState()
    data class DataFetched(val employees : List<Employee>) : EmployeeState()
    data class ErrorState(val message :String) : EmployeeState()

}