package com.faztbit.alwaopportunity.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.faztbit.alwaopportunity.R
import com.faztbit.alwaopportunity.databinding.FragmentMainBinding
import com.faztbit.alwaopportunity.domain.utils.ViewState
import com.faztbit.alwaopportunity.features.utils.collectWhenResumed
import com.faztbit.alwaopportunity.features.utils.handleErrorBase
import com.faztbit.alwaopportunity.features.utils.safeNavigate
import com.faztbit.alwaopportunity.features.utils.viewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModel<MainViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObservers()
        events()
    }

    private fun setUpViews() {
        setupPriorityChart(binding.barCharFirst)
    }

    private fun events() {
        binding.buttonAdd.setOnClickListener {
            findNavController().safeNavigate(MainFragmentDirections.actionFirstFragmentToSecondFragment())
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getMachines()
        viewModel.getFirstChart()
    }

    private fun setUpObservers() {
        val mainAdapter = MainAdapter()
        binding.recyclerViewList.hasFixedSize()
        binding.recyclerViewList.adapter = mainAdapter

        collectWhenResumed(viewModel._machineEvent) {
            binding.progressBar.isVisible = it == ViewState.Loading
            when (it) {
                is ViewState.Error -> handleErrorBase(throwable = it) {

                }

                is ViewState.Success -> {
                    mainAdapter.list = it.data
                }

                else -> {

                }
            }
        }

        collectWhenResumed(viewModel._firstChartEvent) {
            binding.progressBar.isVisible = it == ViewState.Loading
            when (it) {
                is ViewState.Error -> handleErrorBase(throwable = it) {

                }

                else -> {

                }
            }
        }
        collectWhenResumed(viewModel.firstChartList) {
            val dataSets = it.entry.mapIndexed { index, barEntry ->
                BarDataSet(listOf(barEntry), it.labels[index])
            }
            binding.barCharFirst.data = BarData(dataSets)
            binding.barCharFirst.xAxis.valueFormatter = LabelFormatter(it.labels)
            binding.barCharFirst.xAxis.granularity = 1f  // Esto asegura que los índices sean enteros
            binding.barCharFirst.xAxis.isGranularityEnabled = true
            binding.barCharFirst.invalidate()
        }
    }

    private fun setupPriorityChart(chart: BarChart) {
        chart.description.isEnabled = false
        chart.setDrawGridBackground(false)
    }
}