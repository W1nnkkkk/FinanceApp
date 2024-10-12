package com.kirill.finance.presentation.viewmodel.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirill.finance.domain.currency.CurrencyItem
import com.kirill.finance.domain.currency.usecases.GetCurrencyUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val getCurrencyUseCase: GetCurrencyUseCase
) : ViewModel() {

    private var currencyItem = CurrencyItem(0.0, 0.0, 0.0)
    private val _item : MutableLiveData<CurrencyItem> = MutableLiveData(currencyItem)
    val item : LiveData<CurrencyItem> = _item

    init {
        updateCurrency()
    }

    fun updateCurrency() {
        CoroutineScope(Dispatchers.IO).launch {
            _item.postValue(getCurrencyUseCase())
        }
    }
}