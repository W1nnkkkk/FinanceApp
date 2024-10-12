package com.kirill.finance.presentation.viewmodel.operation

import com.kirill.finance.domain.operation.usecases.DeleteOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditCostOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditNameOperationUseCase
import com.kirill.finance.domain.operation.usecases.GetOperationsUseCase
import com.kirill.finance.domain.operation.usecases.InsertOperationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun provideOperationViewModel(
        getOperationsUseCase: GetOperationsUseCase,
        deleteOperationUseCase: DeleteOperationUseCase,
        insertOperationUseCase: InsertOperationUseCase,
        editCostOperationUseCase: EditCostOperationUseCase,
        editNameOperationUseCase: EditNameOperationUseCase
    ) : OperationViewModel {
        return OperationViewModelFactory(
            getOperationsUseCase = getOperationsUseCase,
            deleteOperationUseCase = deleteOperationUseCase,
            insertOperationUseCase = insertOperationUseCase,
            editCostOperationUseCase = editCostOperationUseCase,
            editNameOperationUseCase = editNameOperationUseCase
        ).create(OperationViewModel::class.java)
    }
}