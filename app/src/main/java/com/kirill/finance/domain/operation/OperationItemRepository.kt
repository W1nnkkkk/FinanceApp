package com.kirill.finance.domain.operation

interface OperationItemRepository {
    fun insertOperation(operation: OperationItem)

    fun deleteOperation(operation: OperationItem)

    fun editCostInOperation(id: Int, cost: Int)

    fun editNameInOperation(id: Int, name: String)

    fun getAllOperations() : List<OperationItem>
}