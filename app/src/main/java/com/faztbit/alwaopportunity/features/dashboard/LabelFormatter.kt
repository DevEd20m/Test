package com.faztbit.alwaopportunity.features.dashboard

import com.github.mikephil.charting.formatter.ValueFormatter

class LabelFormatter(private val labels: List<String>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return labels.getOrNull(value.toInt()) ?: value.toString()
    }
}