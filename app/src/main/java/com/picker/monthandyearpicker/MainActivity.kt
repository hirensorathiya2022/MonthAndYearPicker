package com.picker.monthandyearpicker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnOpenPickerDialog = findViewById<Button>(R.id.btnOpenPickerDialog)

        btnOpenPickerDialog.setOnClickListener {
            val monthPickerDialog = MonthPickerDialog.Builder(
                this,
                { selectedMonth, _ ->
                    val formatted = String.format("%02d", selectedMonth + 1)
                    Log.e( "onCreate: ", "formatted => $formatted")
                },
                2025,
                4
            )
                .setActivatedMonth(4)
                .setMinYear(1990)
                .setMaxYear(2030)
                .showMonthOnly()
                .setTitle("Select Month")
                .setPositiveButtonText("Set")
                .setNegativeButtonText("Dismiss")
                .build()

            monthPickerDialog.show()
        }
    }
}