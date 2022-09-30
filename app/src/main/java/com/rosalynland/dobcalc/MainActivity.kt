package com.rosalynland.dobcalc

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this,
            // here we can also remove `DatePickerDialog.OnDateSetListener`
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                // this code is executed after clicking ok, but not on cancel
                Toast.makeText(
                    this,
                    "DatePicker works",
                    Toast.LENGTH_SHORT
                ).show()
            },
            year,
            month,
            day
        ).show()


    }
}