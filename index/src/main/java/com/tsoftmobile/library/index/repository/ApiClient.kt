package com.tsoftmobile.library.index.repository

import com.google.gson.GsonBuilder
import com.tsoftmobile.library.index.Config
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

object ApiClient {

    val service: Service by lazy {
        getRetrofit()
    }

    private fun getRetrofit(): Service {
        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Config.SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(Service::class.java)
    }

}