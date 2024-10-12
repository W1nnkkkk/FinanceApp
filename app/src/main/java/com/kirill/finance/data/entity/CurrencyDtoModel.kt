package com.kirill.finance.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyDtoModel(
    @SerializedName("rates") var values: HashMap<String, Double>
)
