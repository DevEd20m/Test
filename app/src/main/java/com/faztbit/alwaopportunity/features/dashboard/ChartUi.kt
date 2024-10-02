package com.faztbit.alwaopportunity.features.dashboard

import com.github.mikephil.charting.data.BarEntry

data class ChartUi(
    val entry: List<BarEntry>,
    val labels: List<String>
)
