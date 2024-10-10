package com.kirill.finance.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kirill.finance.data.entity.OperationItemDbModel

@Database(entities = [OperationItemDbModel::class], version = 1)
abstract class OperationDatabase : RoomDatabase() {
    abstract fun getOperationItemDAO() : OperationItemDAO
}