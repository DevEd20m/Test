package com.faztbit.alwaopportunity.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faztbit.alwaopportunity.data.database.dao.DataBaseDao
import com.faztbit.alwaopportunity.data.database.models.MachineRoom

@Database(entities = [MachineRoom::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataBaseDao(): DataBaseDao
}
