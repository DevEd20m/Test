package com.faztbit.alwaopportunity.domain.mappers

import com.faztbit.alwaopportunity.data.database.models.MachineRoom
import com.faztbit.alwaopportunity.domain.models.MachineDomain

interface GeneralMapper {
    fun machineRoomListToDomain(param: List<MachineRoom>): List<MachineDomain>
}


class GeneralMapperImpl : GeneralMapper {
    override fun machineRoomListToDomain(param: List<MachineRoom>): List<MachineDomain> {
        return param.map {
            MachineDomain(
                id = it.id,
                name = it.name,
                priority = it.priority,
            )
        }
    }

}