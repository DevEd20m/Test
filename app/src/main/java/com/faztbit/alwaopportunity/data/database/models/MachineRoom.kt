package com.faztbit.alwaopportunity.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Machine")
data class MachineRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("dateTime")
    val dateTime: String,
    @ColumnInfo("priority")
    val priority: String
)
