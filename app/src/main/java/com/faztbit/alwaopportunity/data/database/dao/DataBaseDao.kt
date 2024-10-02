package com.faztbit.alwaopportunity.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.faztbit.alwaopportunity.data.database.models.MachineRoom

@Dao
interface DataBaseDao {

    @Insert
    suspend fun registerMachine(machine: MachineRoom): Long

    @Query("SELECT id, name,dateTime, priority FROM Machine")
    suspend fun getAllMachines(): List<MachineRoom>

    @Update
    suspend fun updateMachine(machine: MachineRoom)

    @Delete
    suspend fun deleteMachine(machine: MachineRoom)
}