package com.kirill.finance.data.datasource.remote

import com.kirill.finance.data.entity.CurrencyDtoModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("RUB")
    fun getCurrency() : Call<CurrencyDtoModel>
}