package com.kirill.finance.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kirill.finance.domain.operation.OperationType

@Entity(tableName = "operaions")
data class OperationItemDbModel(
    val type: String,
    val name: String,
    val cost: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = STANDART_ID_VALUE,
)
{
    companion object {
        private val STANDART_ID_VALUE = 0
    }
}
