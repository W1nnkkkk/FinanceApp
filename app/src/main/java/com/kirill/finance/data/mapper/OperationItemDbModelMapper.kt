package com.kirill.finance.data.mapper

import com.kirill.finance.data.entity.OperationItemDbModel
import com.kirill.finance.domain.operation.OperationItem
import com.kirill.finance.domain.operation.OperationType
import kotlin.math.cos

class OperationItemDbModelMapper {

    fun map(operationItemDbModel: OperationItemDbModel) : OperationItem {
        return with(operationItemDbModel) {
            OperationItem(
                id = id,
                name = name,
                cost = cost,
                type = mapToOperationType(type)
            )
        }
    }

    fun mapToDbModel(operationItem: OperationItem ) : OperationItemDbModel {
        return with(operationItem) {
            OperationItemDbModel(
                id = id,
                name = name,
                cost = cost,
                type = mapFromOperationType(type)
            )
        }
    }

    private fun mapToOperationType(type: String) : OperationType {
        when(type) {
            OperationType.INCOMING.name -> return OperationType.INCOMING
            OperationType.EXPENSES.name -> return OperationType.EXPENSES
            else -> return OperationType.UNKNOWN
        }
    }

    private fun mapFromOperationType(type: OperationType) : String {
        when(type) {
            OperationType.INCOMING -> return OperationType.INCOMING.name
            OperationType.EXPENSES -> return OperationType.EXPENSES.name
            else -> return OperationType.UNKNOWN.name
        }
    }
}