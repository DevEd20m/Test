package com.faztbit.alwaopportunity.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.faztbit.alwaopportunity.data.database.converters.PriorityConverter
import com.faztbit.alwaopportunity.data.database.dao.DataBaseDao
import com.faztbit.alwaopportunity.data.database.models.MachineRoom
import com.faztbit.alwaopportunity.data.database.models.PriorityRoom

@Database(entities = [MachineRoom::class], version = 1, exportSchema = false)
//@TypeConverters(PriorityConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataBaseDao(): DataBaseDao
}
