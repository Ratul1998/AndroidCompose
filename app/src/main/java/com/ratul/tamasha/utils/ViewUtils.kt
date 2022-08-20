package com.ratul.tamasha.utils

import android.content.Context
import android.widget.Toast
import com.ratul.tamasha.screens.MainActivity
import com.ratul.tamasha.viewmodels.EmployeeViewModel
import kotlin.reflect.KProperty

fun Context.toast(message :String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}


operator fun Any.setValue(mainActivity: MainActivity, property: KProperty<*>, employeeViewModel: EmployeeViewModel) {

}
