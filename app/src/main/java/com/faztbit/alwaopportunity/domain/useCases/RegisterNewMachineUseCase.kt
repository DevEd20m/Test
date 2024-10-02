package com.faztbit.alwaopportunity.domain.useCases

import com.faztbit.alwaopportunity.data.repository.MainRepository
import com.faztbit.alwaopportunity.domain.mappers.GeneralMapper
import com.faztbit.alwaopportunity.domain.models.MachineDomain

sealed class RegisterNewMachineUseCaseResult {
    object Success : RegisterNewMachineUseCaseResult()
    data class Warning(val error: String) : RegisterNewMachineUseCaseResult()
}

class RegisterNewMachineUseCase(private val repository: MainRepository, private val mapper: GeneralMapper) {

    suspend fun invoke(item: MachineDomain): RegisterNewMachineUseCaseResult {
        val machine = mapper.machineDomainToRoom(item)
        val existId = repository.registerNewMachines(machine)

        if (existId > 0) {
            return RegisterNewMachineUseCaseResult.Success
        }
        return RegisterNewMachineUseCaseResult.Warning("No se logró registrar máquina")
    }
}