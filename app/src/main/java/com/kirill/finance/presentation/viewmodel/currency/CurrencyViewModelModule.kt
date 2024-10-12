package com.kirill.finance.presentation.viewmodel.currency

import com.kirill.finance.domain.currency.CurrencyItemRepository
import com.kirill.finance.domain.currency.usecases.GetCurrencyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object CurrencyViewModelModule {
    @Provides
    @ActivityScoped
    fun provideCurrencyViewModel(repository: CurrencyItemRepository) : CurrencyViewModel {
        return CurrencyViewModel(
            GetCurrencyUseCase(repository)
        )
    }
}