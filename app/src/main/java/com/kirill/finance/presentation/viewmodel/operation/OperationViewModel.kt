package com.kirill.finance.presentation.viewmodel.operation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirill.finance.domain.operation.usecases.DeleteOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditCostOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditNameOperationUseCase
import com.kirill.finance.domain.operation.usecases.GetOperationsUseCase
import com.kirill.finance.domain.operation.usecases.InsertOperationUseCase
import com.kirill.finance.presentation.operation.OperationListItem
import com.kirill.finance.presentation.operation.mapper.OperationListItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OperationViewModel @Inject constructor(
    private val getOperationsUseCase: GetOperationsUseCase,
    private val deleteOperationUseCase: DeleteOperationUseCase,
    private val insertOperationUseCase: InsertOperationUseCase,
    private val editCostOperationUseCase: EditCostOperationUseCase,
    private val editNameOperationUseCase: EditNameOperationUseCase
) : ViewModel() {
    private val mapper : OperationListItemMapper = OperationListItemMapper()

    private var list: MutableList<OperationListItem> = mutableListOf()
    private val _operationList: MutableLiveData<List<OperationListItem>> =
        MutableLiveData(list)
    val operationList: LiveData<List<OperationListItem>> = _operationList

    init {
        getOperationList()
    }

    fun getOperationList() {
        list = getOperationsUseCase().map { mapper.map(it) }.toMutableList()
        _operationList.value = list
    }

    fun deleteOperation(operation: OperationListItem, position: Int) {
        deleteOperationUseCase(mapper.mapToOperationItem(operation))
        list.removeAt(position)
        _operationList.value = list
    }

    fun insertOperation(operation: OperationListItem) {
        insertOperationUseCase(mapper.mapToOperationItem(operation))
        list.add(0, operation)
        _operationList.value = list
    }

    fun editName(id: Int, name: String) {
        editNameOperationUseCase(id, name)
    }

    fun editCost(id: Int, cost: Int) {
        editCostOperationUseCase(id, cost)
    }

}