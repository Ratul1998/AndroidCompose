package com.ratul.tamasha.data

import com.ratul.tamasha.utils.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface EmployeeService {

    @GET(value = "api/v1/employees")
    suspend fun getEmployees() : Response<EmployeeResponse>

    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): EmployeeService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()


            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://dummy.restapiexample.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EmployeeService::class.java)
        }
    }

}