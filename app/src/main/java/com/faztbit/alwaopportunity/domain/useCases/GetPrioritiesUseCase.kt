package com.faztbit.alwaopportunity.domain.useCases

import com.faztbit.alwaopportunity.data.repository.MainRepository

class GetPrioritiesUseCase(private val repository: MainRepository) {
    fun invoke(): List<String> {
        return repository.getPriorities().map { it.displayName }
    }
}