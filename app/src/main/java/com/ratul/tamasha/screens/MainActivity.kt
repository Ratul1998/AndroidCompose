package com.ratul.tamasha.screens


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ratul.tamasha.components.EmployeeCard
import com.ratul.tamasha.data.Employee
import com.ratul.tamasha.ui.theme.TamashaTheme
import com.ratul.tamasha.utils.Spacing
import com.ratul.tamasha.utils.setValue
import com.ratul.tamasha.viewmodels.EmployeeState
import com.ratul.tamasha.viewmodels.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var employeeViewModel:EmployeeViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TamashaTheme {
                Scaffold(
                    backgroundColor = Color.White,
                    topBar = {
                        TopAppBar(
                            backgroundColor = MaterialTheme.colors.primary,
                            title = {
                                Text(text = "Employees", style = MaterialTheme.typography.h1)
                            },
                        )
                    },
                    modifier = Modifier.padding(bottom = Spacing.Space16)
                ) {

                    val currentState by employeeViewModel.employeeState.collectAsState()

                    when(currentState) {
                        is EmployeeState.Uninitialized->{

                        }

                        is EmployeeState.LoadingState-> {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                        }

                        is EmployeeState.DataFetched-> {
                            EmployeesListView(employees = (currentState as EmployeeState.DataFetched).employees)
                        }

                        is EmployeeState.ErrorState-> {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                Text((currentState as EmployeeState.ErrorState).message, style = MaterialTheme.typography.body2.copy(color = Color.Red))
                            }
                        }
                    }


                }
            }
        }

        employeeViewModel.getEmployees()


    }
}

@Composable
fun EmployeesListView(employees : List<Employee>) {

    LazyColumn{
        items(count = employees.size){
            EmployeeCard(employee = employees[it])
        }
    }

}
