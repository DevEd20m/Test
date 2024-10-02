package com.faztbit.alwaopportunity.domain.useCases

sealed class GetMachinesByPriorityUseCaseResult {
    data class Success(val list: Map<String, Int>) : GetMachinesByPriorityUseCaseResult()
    data class Warning(val error: String) : GetMachinesByPriorityUseCaseResult()
}

class GetMachinesByPriorityUseCase(private val getDataUseCase: GetDataUseCase) {

    suspend fun invoke(): GetMachinesByPriorityUseCaseResult {
        return when (val result = getDataUseCase.invoke()) {
            is GetDataUseCaseResult.Success -> {
                val resultFinal = result.list.groupBy { it.priority }.mapValues { it.value.size }
                GetMachinesByPriorityUseCaseResult.Success(resultFinal)
            }

            is GetDataUseCaseResult.Warning -> {
                GetMachinesByPriorityUseCaseResult.Warning("La data se encuentra vacía")
            }
        }
    }
}