package com.wayne.algorithm.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun DatePickerUsage(){
    val dateBuilder = remember {
        MaterialDatePicker.Builder.datePicker()
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now()).build())
            .build()
    }
    val timeBuilder = remember {
        MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H).build()
    }

    LaunchedEffect(true){
        dateBuilder.addOnPositiveButtonClickListener {

        }
        timeBuilder.addOnPositiveButtonClickListener {

        }

    }
}