package com.kirill.finance.data.repository

import com.kirill.finance.data.datasource.remote.RemoteDataSource
import com.kirill.finance.data.mapper.CurrencyItemDtoModelItemMapper
import com.kirill.finance.domain.currency.CurrencyItem
import com.kirill.finance.domain.currency.CurrencyItemRepository
import javax.inject.Inject

class CurrencyItemRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val mapper : CurrencyItemDtoModelItemMapper
) : CurrencyItemRepository {
    override suspend fun getCurrency(): CurrencyItem {
        return mapper.map(remoteDataSource.getCurrency())
    }
}