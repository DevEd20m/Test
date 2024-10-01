package com.faztbit.alwaopportunity.data.database.converters

import androidx.room.TypeConverter
import com.faztbit.alwaopportunity.data.database.models.PriorityRoom

class PriorityConverter {

    @TypeConverter
    fun fromTaskStatus(status: PriorityRoom): String {
        return status.name
    }

    @TypeConverter
    fun toTaskStatus(status: String): PriorityRoom {
        return PriorityRoom.valueOf(status)
    }
}