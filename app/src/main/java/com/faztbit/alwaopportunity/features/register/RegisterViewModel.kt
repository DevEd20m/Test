package com.faztbit.alwaopportunity.features.register

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faztbit.alwaopportunity.domain.models.MachineDomain
import com.faztbit.alwaopportunity.domain.useCases.GetPrioritiesUseCase
import com.faztbit.alwaopportunity.domain.useCases.RegisterNewMachineUseCase
import com.faztbit.alwaopportunity.domain.useCases.RegisterNewMachineUseCaseResult
import com.faztbit.alwaopportunity.domain.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterNewMachineUseCase,
    private val getPriorities: GetPrioritiesUseCase
) : ViewModel() {

    val _registerEvent: MutableLiveData<ViewState<Unit>> = MutableLiveData()
    val _prioritiesEvent: MutableLiveData<List<String>> = MutableLiveData()

    val description = MutableLiveData<String>(null)
    val lastUpdated = MutableLiveData<String>(null)
    val priority = MutableLiveData<String>(null)

    val statusButton = MediatorLiveData<Boolean>().apply {
        addSource(description) { validateForm() }
        addSource(lastUpdated) { validateForm() }
        addSource(priority) { validateForm() }
    }

    init {
        _prioritiesEvent.value = getPriorities.invoke()
    }

    private fun validateForm() {
        val validForm: () -> Boolean = {
            when {
                description.value.isNullOrEmpty() -> false
                lastUpdated.value.isNullOrEmpty() -> false
                priority.value.isNullOrEmpty() -> false
                else -> true
            }
        }
        statusButton.value = validForm()
    }


    fun registerMachine(name: String, dateTime: String, priority: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val machineDomain = MachineDomain(
                name = name,
                dateTime = dateTime,
                priority = priority
            )
            _registerEvent.postValue(ViewState.Loading)
            when (val result = registerUseCase.invoke(item = machineDomain)) {
                is RegisterNewMachineUseCaseResult.Warning -> {
                    _registerEvent.postValue(ViewState.Error(result.error))
                }

                RegisterNewMachineUseCaseResult.Success -> {
                    _registerEvent.postValue(ViewState.Success(Unit))
                }
            }
        }
}