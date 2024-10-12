package com.kirill.finance.domain.currency

import androidx.annotation.DoNotInline

data class CurrencyItem(
    val rusValue: Double,
    val usdValue: Double,
    val eurValue: Double
)
