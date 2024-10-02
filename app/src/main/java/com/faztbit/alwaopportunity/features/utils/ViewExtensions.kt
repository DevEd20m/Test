package com.faztbit.alwaopportunity.features.utils

import android.app.DatePickerDialog
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.faztbit.alwaopportunity.R
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

fun TextInputEditText.showDatePicker(minYears: Int, maxYears: Int, minMonths: Int = 0, maxMonths: Int = 0) {
    this.setOnClickListener {
        val date = this.text.toString().trim().split("/")
        val calendar = Calendar.getInstance()

        val year = if (date.size == 3) date[2].toIntOrNull() else calendar.get(Calendar.YEAR)
        val month = if (date.size == 3) date[1].toIntOrNull()?.minus(1) else calendar.get(Calendar.MONTH)
        val day = if (date.size == 3) date[0].toIntOrNull() else calendar.get(Calendar.DAY_OF_MONTH)

        this.pickDateAndDisplayOnIt(
            year ?: 0,
            month ?: 0,
            day ?: 0,
            calculateDateInMillis(-minYears, -minMonths),
            calculateDateInMillis(maxYears, maxMonths)
        )
    }
}

private fun TextInputEditText.pickDateAndDisplayOnIt(
    year: Int,
    month: Int,
    day: Int,
    minDate: Long,
    maxDate: Long
) {
    val theme = R.style.CustomDatePickerDialogTheme
    val picker = DatePickerDialog(context, theme, { _, y, m, d ->
        this.setText("${appendZeroIfNeeded(d)}/${appendZeroIfNeeded(m + 1)}/$y")
    }, year, month, day)
    picker.datePicker.minDate = minDate
    picker.datePicker.maxDate = maxDate
    picker.show()
}

fun AutoCompleteTextView.setDataToDropdown(list: List<String>) {
    val adapter = ArrayAdapter(context.applicationContext, android.R.layout.simple_dropdown_item_1line, list)
    this.setAdapter(adapter)
}
