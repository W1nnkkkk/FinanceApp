package com.kirill.finance.data.mapper

import com.kirill.finance.data.entity.CurrencyDtoModel
import com.kirill.finance.domain.currency.CurrencyItem

class CurrencyItemDtoModelItemMapper {
    fun map(currencyDtoModel: CurrencyDtoModel) : CurrencyItem {
        var rubValue = 0.0
        var usdValue = 0.0
        var eurValue = 0.0
        for (item in currencyDtoModel.values) {
            when(item.key) {
                "RUB" -> rubValue = item.value
                "USD" -> usdValue = item.value
                "EUR" -> eurValue = item.value
            }
        }
        return CurrencyItem(
            rusValue = rubValue,
            usdValue = usdValue,
            eurValue = eurValue
        )
    }

}