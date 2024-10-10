package com.kirill.finance.domain.operation.usecases

import com.kirill.finance.domain.operation.OperationItem
import com.kirill.finance.domain.operation.OperationItemRepository
import javax.inject.Inject

class DeleteOperationUseCase @Inject constructor(
    private val repository: OperationItemRepository
) {
    operator fun invoke(operationItem: OperationItem) {
        repository.deleteOperation(operationItem)
    }
}