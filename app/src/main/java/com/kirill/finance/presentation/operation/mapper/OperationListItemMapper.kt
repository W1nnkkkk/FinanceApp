package com.kirill.finance.presentation.operation.mapper

import com.kirill.finance.R
import com.kirill.finance.domain.operation.OperationItem
import com.kirill.finance.domain.operation.OperationType
import com.kirill.finance.presentation.operation.OperationListItem

class OperationListItemMapper {

    fun map(operationItem: OperationItem) : OperationListItem {
        return with(operationItem) {
            OperationListItem(
                id = id,
                name = name,
                cost = cost,
                typeImage = mapOperationToImage(type)
            )
        }
    }

    fun mapToOperationItem(operationListItem: OperationListItem) : OperationItem {
        return with(operationListItem) {
            OperationItem(
                id = id,
                name = name,
                cost = cost,
                type = mapFromImageToOperation(typeImage)
            )
        }
    }

    private fun mapOperationToImage(type: OperationType) : Int {
        when(type) {
            OperationType.INCOMING -> return R.drawable.ic_incoming
            OperationType.EXPENSES -> return R.drawable.ic_expenses
            else -> return R.drawable.ic_unknown
        }
    }

    private fun mapFromImageToOperation(imageId: Int) : OperationType {
        when(imageId) {
            R.drawable.ic_incoming -> return OperationType.INCOMING
            R.drawable.ic_expenses -> return OperationType.EXPENSES
            else -> return OperationType.UNKNOWN
        }
    }
}