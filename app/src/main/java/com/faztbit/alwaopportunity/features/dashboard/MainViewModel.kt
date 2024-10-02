package com.faztbit.alwaopportunity.features.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faztbit.alwaopportunity.domain.useCases.GetDataUseCase
import com.faztbit.alwaopportunity.domain.useCases.GetDataUseCaseResult
import com.faztbit.alwaopportunity.domain.models.MachineDomain
import com.faztbit.alwaopportunity.domain.useCases.GetMachinesByPriorityUseCase
import com.faztbit.alwaopportunity.domain.useCases.GetMachinesByPriorityUseCaseResult
import com.faztbit.alwaopportunity.domain.utils.ViewState
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getDataUseCase: GetDataUseCase,
    private val getFirstChart: GetMachinesByPriorityUseCase
) : ViewModel() {
    val _firstChartEvent: MutableLiveData<ViewState<Unit>> = MutableLiveData()
    val firstChartList = MutableLiveData<ChartUi>()
    val _machineEvent: MutableLiveData<ViewState<List<MachineDomain>>> = MutableLiveData()
    fun getMachines() = viewModelScope.launch(Dispatchers.IO) {
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

    fun getFirstChart() = viewModelScope.launch(Dispatchers.IO) {
        _firstChartEvent.postValue(ViewState.Loading)
        when (val result = getFirstChart.invoke()) {
            is GetMachinesByPriorityUseCaseResult.Warning -> {
                _firstChartEvent.postValue(ViewState.Error(result.error))
            }

            is GetMachinesByPriorityUseCaseResult.Success -> {
                val labels = result.list.keys.toList()
                val newData = result.list.entries.mapIndexed { index, entry ->
                    BarEntry(index.toFloat(), entry.value.toFloat())
                }
                firstChartList.postValue(
                    ChartUi(
                        entry = newData,
                        labels = labels
                    )
                )
                _firstChartEvent.postValue(ViewState.Success(Unit))
            }
        }
    }
}