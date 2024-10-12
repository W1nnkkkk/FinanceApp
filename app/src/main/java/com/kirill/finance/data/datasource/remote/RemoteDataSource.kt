package com.kirill.finance.data.datasource.remote

import com.kirill.finance.data.entity.CurrencyDtoModel
import retrofit2.http.GET

interface RemoteDataSource {
    suspend fun getCurrency() : CurrencyDtoModel
}