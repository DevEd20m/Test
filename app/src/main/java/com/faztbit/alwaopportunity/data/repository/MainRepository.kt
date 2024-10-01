package com.faztbit.alwaopportunity.data.repository

import com.faztbit.alwaopportunity.data.database.dao.DataBaseDao
import com.faztbit.alwaopportunity.data.database.models.MachineRoom

interface MainRepository {
    suspend fun registerNewMachines(machineRoom: MachineRoom)
    suspend fun getAllMachines(): List<MachineRoom>
}

class MainRepositoryImpl(private val dao: DataBaseDao) : MainRepository {
    override suspend fun registerNewMachines(machineRoom: MachineRoom) {
        dao.registerMachine(machineRoom)
    }

    override suspend fun getAllMachines(): List<MachineRoom> {
        return dao.getAllMachines()
    }
}