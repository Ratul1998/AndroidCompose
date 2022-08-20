package com.ratul.tamasha.utils.network

import com.ratul.tamasha.utils.Exceptions
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun<T : Any> apiRequest(call : suspend () -> Response<T>) : T {
        val response = call.invoke()
        if(response.code() == 200){
            return response.body()!!
        }
        else{
            val message = StringBuilder()
            message.append("Something went wrong with error code : ${response.code()}")
            throw Exceptions.ApiException(message.toString())
        }
    }

}