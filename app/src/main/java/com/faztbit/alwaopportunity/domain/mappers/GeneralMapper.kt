package com.faztbit.alwaopportunity.domain.mappers

import com.faztbit.alwaopportunity.data.database.models.MachineRoom
import com.faztbit.alwaopportunity.domain.models.MachineDomain

interface GeneralMapper {
    fun machineRoomListToDomain(param: List<MachineRoom>): List<MachineDomain>
    fun machineDomainToRoom(item: MachineDomain): MachineRoom
}


class GeneralMapperImpl : GeneralMapper {
    override fun machineRoomListToDomain(param: List<MachineRoom>): List<MachineDomain> {
        return param.map {
            MachineDomain(
                id = it.id,
                name = it.name,
                dateTime = it.dateTime,
                priority = it.priority,
            )
        }
    }

    override fun machineDomainToRoom(item: MachineDomain): MachineRoom {
        return MachineRoom(
            id = item.id ?: 0,
            name = item.name,
            dateTime = item.dateTime,
            priority = item.priority,
        )
    }

}