package com.kirill.finance.data.datasource.local

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.kirill.finance.data.entity.OperationItemDbModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val context: Context
) : LocalDataSource {

    val db: OperationDatabase = Room.databaseBuilder(context = context,
        OperationDatabase::class.java, "OperationDB").build()

    override fun insertOperation(operation: OperationItemDbModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.getOperationItemDAO().insertOperation(operation)
        }
    }

    override fun deleteOperation(operation: OperationItemDbModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.getOperationItemDAO().deleteOperation(operation)
        }
    }

    override fun editCostInOperation(id: Int, cost: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            db.getOperationItemDAO().editCostInOperation(id, cost)
        }
    }

    override fun editNameInOperation(id: Int, name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            db.getOperationItemDAO().editNameInOperation(id, name)
        }
    }

    override fun getAllOperations(): List<OperationItemDbModel> {
        var list: List<OperationItemDbModel> = emptyList()
        runBlocking {
            val deffer = CoroutineScope(Dispatchers.IO).async {
                return@async db.getOperationItemDAO().getAllOperations()
            }
            list = deffer.await()
        }
        return list
    }
}