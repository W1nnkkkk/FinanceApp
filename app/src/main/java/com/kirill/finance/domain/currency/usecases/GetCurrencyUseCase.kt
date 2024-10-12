package com.kirill.finance.domain.currency.usecases

import com.kirill.finance.domain.currency.CurrencyItem
import com.kirill.finance.domain.currency.CurrencyItemRepository
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val repository: CurrencyItemRepository
) {
    suspend operator fun invoke() : CurrencyItem {
        return repository.getCurrency()
    }
}