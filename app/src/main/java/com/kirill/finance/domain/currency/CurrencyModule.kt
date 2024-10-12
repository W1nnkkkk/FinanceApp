package com.kirill.finance.domain.currency

import com.kirill.finance.data.datasource.remote.RemoteDataSource
import com.kirill.finance.data.datasource.remote.RemoteDataSourceImpl
import com.kirill.finance.data.mapper.CurrencyItemDtoModelItemMapper
import com.kirill.finance.data.repository.CurrencyItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource() : RemoteDataSource {
        return RemoteDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        remoteDataSource: RemoteDataSource
    ) : CurrencyItemRepository {
        return CurrencyItemRepositoryImpl(remoteDataSource, CurrencyItemDtoModelItemMapper())
    }
}