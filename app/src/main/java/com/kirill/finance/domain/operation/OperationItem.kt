package com.kirill.finance.domain.operation

data class OperationItem(
    val type: OperationType,
    val name: String,
    val cost: Int,
    val id: Int = STANDART_ID_VALUE
)
{
    companion object {
        private val STANDART_ID_VALUE = 0
    }
}
