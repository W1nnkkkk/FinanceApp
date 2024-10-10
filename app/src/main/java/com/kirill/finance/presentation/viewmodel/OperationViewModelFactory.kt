package com.kirill.finance.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kirill.finance.domain.operation.usecases.DeleteOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditCostOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditNameOperationUseCase
import com.kirill.finance.domain.operation.usecases.GetOperationsUseCase
import com.kirill.finance.domain.operation.usecases.InsertOperationUseCase
import javax.inject.Inject

class OperationViewModelFactory @Inject constructor(
    private val getOperationsUseCase: GetOperationsUseCase,
    private val deleteOperationUseCase: DeleteOperationUseCase,
    private val insertOperationUseCase: InsertOperationUseCase,
    private val editCostOperationUseCase: EditCostOperationUseCase,
    private val editNameOperationUseCase: EditNameOperationUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OperationViewModel(
            getOperationsUseCase = getOperationsUseCase,
            deleteOperationUseCase = deleteOperationUseCase,
            insertOperationUseCase = insertOperationUseCase,
            editCostOperationUseCase = editCostOperationUseCase,
            editNameOperationUseCase = editNameOperationUseCase
        ) as T
    }
}