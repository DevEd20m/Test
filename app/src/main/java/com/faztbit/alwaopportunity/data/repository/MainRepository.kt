package com.faztbit.alwaopportunity.data.repository

import com.faztbit.alwaopportunity.data.database.dao.DataBaseDao
import com.faztbit.alwaopportunity.data.database.models.MachineRoom
import com.faztbit.alwaopportunity.domain.models.PriorityDomain

interface MainRepository {
    suspend fun registerNewMachines(machineRoom: MachineRoom): Long
    suspend fun getAllMachines(): List<MachineRoom>
    fun getPriorities(): List<PriorityDomain>
}

class MainRepositoryImpl(private val dao: DataBaseDao) : MainRepository {
    override suspend fun registerNewMachines(machineRoom: MachineRoom): Long {
        return dao.registerMachine(machineRoom)
    }

    override suspend fun getAllMachines(): List<MachineRoom> {
        return dao.getAllMachines()
    }

    override fun getPriorities(): List<PriorityDomain> {
        return listOf(PriorityDomain.LOW, PriorityDomain.MEDIUM, PriorityDomain.HIGH)
    }
}