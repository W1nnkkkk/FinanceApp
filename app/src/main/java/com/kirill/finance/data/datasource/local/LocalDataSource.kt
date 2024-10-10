package com.kirill.finance.data.datasource.local

import com.kirill.finance.data.entity.OperationItemDbModel


interface LocalDataSource {
    fun insertOperation(operation: OperationItemDbModel)

    fun deleteOperation(operation: OperationItemDbModel)

    fun editCostInOperation(id: Int, cost: Int)

    fun editNameInOperation(id: Int, name: String)

    fun getAllOperations() : List<OperationItemDbModel>
}