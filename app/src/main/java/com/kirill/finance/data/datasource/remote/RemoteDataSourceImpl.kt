package com.kirill.finance.data.datasource.remote


import android.util.Log
import com.kirill.finance.data.entity.CurrencyDtoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.await
import retrofit2.awaitResponse

class RemoteDataSourceImpl : RemoteDataSource {
    private val apiService = ApiClient.retrofit.create(ApiService::class.java)

    override suspend fun getCurrency(): CurrencyDtoModel {
        var hashMap = apiService.getCurrency().await().values
        return CurrencyDtoModel(hashMap)
    }
}