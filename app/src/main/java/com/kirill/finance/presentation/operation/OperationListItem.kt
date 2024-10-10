package com.kirill.finance.presentation.operation

import android.os.Parcelable
import com.kirill.finance.domain.operation.OperationType
import kotlinx.serialization.Serializable

@Serializable
data class OperationListItem (
    var typeImage: Int,
    var name: String,
    var cost: Int,
    val id: Int = STANDART_ID_VALUE
)
{
    companion object {
        private val STANDART_ID_VALUE = 0
    }
}
