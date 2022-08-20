package com.ratul.tamasha.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ratul.tamasha.data.Employee
import com.ratul.tamasha.utils.Spacing
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun EmployeeCard(employee: Employee) {

    val expanded = remember {
        mutableStateOf(false)
    }

    val modifier = Modifier
        .padding(all = Spacing.Space8)
        .fillMaxWidth()
        .clickable (onClick = {expanded.value = !expanded.value})

    Card(elevation = Spacing.Space4, backgroundColor = Color.White, modifier = modifier, shape = RoundedCornerShape(size = Spacing.Space8)){
        Row(Modifier.padding(all = Spacing.Space8), verticalAlignment = Alignment.CenterVertically) {
            ImageCard()
            Column() {
                Text(text = employee.employee_name, style = MaterialTheme.typography.h2)
                Box(modifier = Modifier.padding(bottom = Spacing.Space8))
                Row{
                    Text(text = "Employee Id : ${employee.id}", style = MaterialTheme.typography.body1)
                    Box(modifier = Modifier.weight(weight = 1f))
                    Text(text = "Age : ${employee.employee_age} years",style = MaterialTheme.typography.body1)
                }
                if(expanded.value){
                    Box(modifier = Modifier.padding(bottom = Spacing.Space4))
                    Box(modifier = Modifier.fillMaxWidth().height(height = 1.dp).background(color = Color.LightGray))
                    Box(modifier = Modifier.padding(bottom = Spacing.Space4))
                    Text(text = "Current Salary : ₹ ${employee.employee_salary} /month",style = MaterialTheme.typography.body2)
                }

            }


        }
    }

}

val profilePhotos = listOf<String>("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzHQv_th9wq3ivQ1CVk7UZRxhbPq64oQrg5Q&usqp=CAU",
    "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmlsZXxlbnwwfHwwfHw%3D&w=1000&q=80",
    "https://www.rd.com/wp-content/uploads/2017/09/01-shutterstock_476340928-Irina-Bg.jpg?fit=640,427",
    "https://expertphotography.b-cdn.net/wp-content/uploads/2020/08/social-media-profile-photos-3.jpg",
    "https://i.insider.com/59b6c4bfba785e36f932a317?width=600&format=jpeg&auto=webp")

@Composable
fun ImageCard() {

    val index = (profilePhotos.indices).random()

    val modifier = Modifier
        .padding(end = Spacing.Space16)
        .height(height = Spacing.Space56)
        .width(width = Spacing.Space56)

    Card(elevation = 2.dp, backgroundColor = Color.White, modifier = modifier, shape = CircleShape){
        GlideImage(imageModel = profilePhotos[index], contentScale = ContentScale.Crop,)
    }

}
