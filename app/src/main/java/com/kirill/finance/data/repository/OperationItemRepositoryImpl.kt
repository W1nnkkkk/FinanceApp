package com.kirill.finance.data.repository

import com.kirill.finance.data.datasource.local.LocalDataSource
import com.kirill.finance.data.mapper.OperationItemDbModelMapper
import com.kirill.finance.domain.operation.OperationItem
import com.kirill.finance.domain.operation.OperationItemRepository
import javax.inject.Inject

class OperationItemRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val mapper: OperationItemDbModelMapper = OperationItemDbModelMapper()
) : OperationItemRepository {
    override fun insertOperation(operation: OperationItem) {
        localDataSource.insertOperation(mapper.mapToDbModel(operation))
    }

    override fun deleteOperation(operation: OperationItem) {
        localDataSource.deleteOperation(mapper.mapToDbModel(operation))
    }

    override fun editCostInOperation(id: Int, cost: Int) {
        localDataSource.editCostInOperation(id, cost)
    }

    override fun editNameInOperation(id: Int, name: String) {
        localDataSource.editNameInOperation(id, name)
    }

    override fun getAllOperations(): List<OperationItem> {
        return localDataSource.getAllOperations().map { mapper.map(it) }
    }
}