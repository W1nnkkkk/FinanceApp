package com.kirill.finance.domain.operation

import android.content.Context
import androidx.room.Delete
import com.kirill.finance.data.datasource.local.LocalDataSource
import com.kirill.finance.data.datasource.local.LocalDataSourceImpl
import com.kirill.finance.data.repository.OperationItemRepositoryImpl
import com.kirill.finance.domain.operation.usecases.DeleteOperationUseCase
import com.kirill.finance.domain.operation.usecases.GetOperationsUseCase
import com.kirill.finance.domain.operation.usecases.InsertOperationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OperationModule {
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(context: Context) : LocalDataSource {
        return LocalDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideOperationItemRepostitory(localDataSource: LocalDataSource)
    : OperationItemRepository {
        return OperationItemRepositoryImpl(localDataSource)
    }
}