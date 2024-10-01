package com.faztbit.alwaopportunity.domain

import com.faztbit.alwaopportunity.data.repository.MainRepository
import com.faztbit.alwaopportunity.domain.mappers.GeneralMapper
import com.faztbit.alwaopportunity.domain.models.MachineDomain

sealed class GetDataUseCaseResult {
    data class Success(val list: List<MachineDomain>) : GetDataUseCaseResult()
    data class Warning(val error: String) : GetDataUseCaseResult()
}

class GetDataUseCase(private val repository: MainRepository, private val mapper: GeneralMapper) {
    suspend fun invoke(): GetDataUseCaseResult {
        val list = repository.getAllMachines()
        val result = mapper.machineRoomListToDomain(list)
        if (result.isEmpty()) {
            return GetDataUseCaseResult.Warning("No existe ninguna máquina registrada aún")
        }
        return GetDataUseCaseResult.Success(result)
    }
}