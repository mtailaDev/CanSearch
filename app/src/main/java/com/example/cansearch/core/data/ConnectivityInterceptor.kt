package com.example.cansearch.core.data

import android.content.Context
import android.net.ConnectivityManager
import com.example.cansearch.App
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasConnection()) throw NoConnectionError("No Internet Connection")
        return chain.proceed(chain.request())
    }

    private fun hasConnection(): Boolean {
        val connectivityManager = App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    inner class NoConnectionError(message: String) : Throwable(message)
}