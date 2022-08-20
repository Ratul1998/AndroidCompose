package com.ratul.tamasha.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.ratul.tamasha.utils.Exceptions
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(@ApplicationContext private val context: Context) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable()){

            throw Exceptions.NoNetworkException("No Internet available")
        }

        var response = chain.proceed(chain.request())

        var tryCount = 0
        while (!response.isSuccessful && tryCount < 5) {

            tryCount++

            response.close()
            response = chain.proceed(chain.request())
        }

        return response

    }

    private  fun isInternetAvailable() : Boolean{

        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var result = false

        connectivityManager.run {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }

        return result

    }
}