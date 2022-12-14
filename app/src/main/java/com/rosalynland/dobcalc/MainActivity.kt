package com.rosalynland.dobcalc

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            // here we can also remove `DatePickerDialog.OnDateSetListener`
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                // this code is executed after clicking ok, but not on cancel
                Toast.makeText(
                    this,
                    "dayOfMonth: $selectedDayOfMonth, month: ${selectedMonth + 1}, year: $selectedYear",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                // setting the desired date format
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                // converting date into our selected format
                val convertedDate = sdf.parse(selectedDate)

                // if let convertedDate = convertedDate
                convertedDate?.let {
                    val selectedDateInMinutes = convertedDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }


            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86_400_000
        dpd.show()
    }
}