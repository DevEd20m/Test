package com.faztbit.alwaopportunity.features.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faztbit.alwaopportunity.domain.GetDataUseCase
import com.faztbit.alwaopportunity.domain.GetDataUseCaseResult
import com.faztbit.alwaopportunity.domain.models.MachineDomain
import com.faztbit.alwaopportunity.domain.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val getDataUseCase: GetDataUseCase) : ViewModel() {
    val _machineEvent: MutableLiveData<ViewState<List<MachineDomain>>> = MutableLiveData()
    fun getMachines(idCourse: String, type: String) = viewModelScope.launch(Dispatchers.IO) {
        _machineEvent.postValue(ViewState.Loading)
        when (val result = getDataUseCase.invoke()) {
            is GetDataUseCaseResult.Warning -> {
                _machineEvent.postValue(ViewState.Error(result.error))
            }

            is GetDataUseCaseResult.Success -> {
                val newData = result.list
                _machineEvent.postValue(ViewState.Success(newData))
            }
        }
    }
}