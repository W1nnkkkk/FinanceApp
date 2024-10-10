package com.kirill.finance.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kirill.finance.data.entity.OperationItemDbModel

@Dao
interface OperationItemDAO {
    @Insert
    fun insertOperation(operation: OperationItemDbModel)

    @Delete
    fun deleteOperation(operation: OperationItemDbModel)

    @Query("UPDATE operaions SET cost = :cost WHERE id = :id")
    fun editCostInOperation(id: Int, cost: Int)

    @Query("UPDATE operaions SET name = :name WHERE id = :id")
    fun editNameInOperation(id: Int, name: String)

    @Query("SELECT * FROM operaions ORDER BY id DESC")
    fun getAllOperations() : List<OperationItemDbModel>
}