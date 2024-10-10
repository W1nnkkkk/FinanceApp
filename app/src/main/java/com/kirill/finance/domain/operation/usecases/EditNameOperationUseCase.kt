package com.kirill.finance.domain.operation.usecases

import com.kirill.finance.domain.operation.OperationItemRepository
import javax.inject.Inject

class EditNameOperationUseCase @Inject constructor(
    private val repository: OperationItemRepository
) {
    operator fun invoke(id: Int, name: String) {
        repository.editNameInOperation(id, name)
    }
}