package com.kirill.finance.domain.currency

interface CurrencyItemRepository {
    suspend fun getCurrency() : CurrencyItem
}