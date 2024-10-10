package com.kirill.finance.presentation.operation

import com.kirill.finance.domain.operation.OperationItemRepository
import com.kirill.finance.domain.operation.usecases.DeleteOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditCostOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditNameOperationUseCase
import com.kirill.finance.domain.operation.usecases.GetOperationsUseCase
import com.kirill.finance.domain.operation.usecases.InsertOperationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OperationActivityModule {

    @Provides
    @Singleton
    fun provideGetOperationsUseCase(
        repository: OperationItemRepository
    ): GetOperationsUseCase {
        return GetOperationsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertOperationsUseCase(
        repository: OperationItemRepository
    ): InsertOperationUseCase {
        return InsertOperationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteOperationsUseCase(
        repository: OperationItemRepository
    ): DeleteOperationUseCase {
        return DeleteOperationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideEditNameOperationsUseCase(
        repository: OperationItemRepository
    ): EditNameOperationUseCase {
        return EditNameOperationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideEditCostOperationsUseCase(
        repository: OperationItemRepository
    ): EditCostOperationUseCase {
        return EditCostOperationUseCase(repository)
    }
}

