package com.example.age_calculater

//import android.app.DatePickerDialog
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var btn_birth: Button
//    private lateinit var btn_today: Button
//    private lateinit var btn_calculate: Button
//    private lateinit var tvResult: TextView
//    private lateinit var dateSetListener1: DatePickerDialog.OnDateSetListener
//    private lateinit var dateSetListener2: DatePickerDialog.OnDateSetListener
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}



import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Period
import org.joda.time.DateTime
import org.joda.time.PeriodType
import java.util.*

class MainActivity : AppCompatActivity() {

    // initializing variables
    private lateinit var btn_birth: Button
    private lateinit var btn_today: Button
    private lateinit var btn_calculate: Button
    private lateinit var tvResult: TextView
    private lateinit var dateSetListener1: DatePickerDialog.OnDateSetListener
    private lateinit var dateSetListener2: DatePickerDialog.OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // assign variables
        btn_birth = findViewById(R.id.bt_birth)
        btn_today = findViewById(R.id.bt_today)
        btn_calculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        // calendar format is imported to pick date
        val calendar = Calendar.getInstance()

        // for year
        val year = calendar.get(Calendar.YEAR)

        // for month
        val month = calendar.get(Calendar.MONTH)

        // for date
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        // to set the current date as by default
        val date = simpleDateFormat.format(Calendar.getInstance().time)
        btn_today.text = date

        // action to be performed when button 1 is clicked
        btn_birth.setOnClickListener {
            // date picker dialog is used
            // and its style and color are also passed
            val datePickerDialog = DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener1,
                year,
                month,
                day
            )
            // to set background for datepicker
            datePickerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePickerDialog.show()
        }

        // it is used to set the date which user selects
        dateSetListener1 = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // here month+1 is used so that
            // actual month number can be displayed
            // otherwise it starts from 0 and it shows
            // 1 number less for every month
            // example- for january month=0
            var selectedMonth = month + 1
            val date = "$day/$selectedMonth/$year"
            btn_birth.text = date
        }

        // action to be performed when button 2 is clicked
        btn_today.setOnClickListener {
            // date picker dialog is used
            // and its style and color are also passed
            val datePickerDialog = DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener2,
                year,
                month,
                day
            )
            // to set background for datepicker
            datePickerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePickerDialog.show()
        }

        // it is used to set the date which user selects
        dateSetListener2 = DatePickerDialog.OnDateSetListener { _, year, month, day ->

            var selectedMonth = month + 1
            val date = "$day/$selectedMonth/$year"
            btn_today.text = date
        }
        btn_calculate.setOnClickListener {

            // converting the inputted date to string
            val sDate = btn_birth.text.toString()
            val eDate = btn_today.text.toString()

            val simpleDateFormat1 = SimpleDateFormat("dd/MM/yyyy")

            try {
                // converting it to date format
                val date1 = simpleDateFormat1.parse(sDate)
                val date2 = simpleDateFormat1.parse(eDate)

                val startdate = date1.time
                val endDate = date2.time

                // condition
                if (startdate <= endDate) {
                    val period = org.joda.time.Period(startdate, endDate, PeriodType.yearMonthDay())
                    val years = period.years
                    val months = period.months
                    val days = period.days

                    // show the final output
                    tvResult.text = "$years Years | $months Months | $days Days"
                } else {
                    // show message
                    Toast.makeText(this@MainActivity, "BirthDate should not be larger than today's date!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
    }
}



