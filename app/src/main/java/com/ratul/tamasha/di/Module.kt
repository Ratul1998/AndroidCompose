package com.ratul.tamasha.di

import com.ratul.tamasha.data.EmployeeService
import com.ratul.tamasha.utils.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    fun provideEmployeesService(interceptor: NetworkConnectionInterceptor) : EmployeeService{
        return EmployeeService.invoke(interceptor)
    }

}