package com.kirill.finance.data.datasource.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val api = "https://api.exchangerate-api.com/v4/latest/"

    private val httpClient = OkHttpClient.Builder().build()

    val retrofit = Retrofit
            .Builder()
            .baseUrl(api)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}