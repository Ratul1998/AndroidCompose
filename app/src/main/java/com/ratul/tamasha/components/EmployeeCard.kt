package com.ratul.tamasha.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ratul.tamasha.R
import com.ratul.tamasha.data.Employee
import com.ratul.tamasha.utils.Spacing

@Composable
fun EmployeeCard(employee: Employee) {

    val expanded = remember {
        mutableStateOf(false)
    }

    val modifier = Modifier
        .padding(all = Spacing.Space8)
        .fillMaxWidth()
        .clickable {
            expanded.value = !expanded.value
        }

    Card(elevation = Spacing.Space4, backgroundColor = Color.White, modifier = modifier, shape = RoundedCornerShape(size = Spacing.Space8)){
        Row(Modifier.padding(all = Spacing.Space8), verticalAlignment = Alignment.CenterVertically) {
            ImageCard(contentDescription = "Profile Photo")
            Column() {
                Text(text = employee.employee_name, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium))
                Row{
                    Text(text = "Employee Id : ${employee.id}", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light, color = Color.DarkGray))
                    Box(modifier = Modifier.weight(weight = 1f))
                    Text(text = "Age : ${employee.employee_age} years",style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light, color = Color.DarkGray))
                }
                if(expanded.value){
                    Text(text = "Current Salary : ₹ ${employee.employee_salary}",style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Green))
                }

            }


        }
    }

}

@Composable
fun ImageCard(contentDescription: String) {

    val modifier = Modifier
        .padding(end = Spacing.Space16)
        .height(height = Spacing.Space56)
        .width(width = Spacing.Space56)

    Card(elevation = 2.dp, backgroundColor = Color.White, modifier = modifier, shape = RoundedCornerShape(size = Spacing.Space8)){
        Image(painter = painterResource(id = R.drawable.ic_baseline_person_24), contentDescription = contentDescription)
    }

}
