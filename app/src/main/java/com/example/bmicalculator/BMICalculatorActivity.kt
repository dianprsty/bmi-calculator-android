package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.tugasharianandroid.databinding.ActivityBmicalculatorBinding

class BMICalculatorActivity : AppCompatActivity() {
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var tvResultCategory: TextView

    private lateinit var binding: ActivityBmicalculatorBinding
    private lateinit var bmiCalculatorViewModel: BMICalculatorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmicalculatorBinding.inflate(layoutInflater)

        bmiCalculatorViewModel = ViewModelProvider(this)[BMICalculatorViewModel::class.java]
        setContentView(binding.root)
        edtWeight = binding.edtWeight
        edtHeight = binding.edtHeight
        btnCalculate = binding.btnCalculate
        tvResult = binding.tvResult
        tvResultCategory = binding.tvResultCategory

        //Observer Live Data
        bmiCalculatorViewModel.currentResult().observe(this) {
            tvResult.text = it
        }

        bmiCalculatorViewModel.currentResultCategory().observe(this) {
            tvResultCategory.text = it
        }

        bmiCalculatorViewModel.currentWeightWarning().observe(this) {
            if (it.isNotEmpty()) {
                edtWeight.error = it
            }
        }

        bmiCalculatorViewModel.currentHeightWarning().observe(this) {
            if (it.isNotEmpty()) {
                edtHeight.error = it
            }
        }

        btnCalculate.setOnClickListener {
            val inputWeight = edtWeight.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()
            bmiCalculatorViewModel.clearWarning()
            bmiCalculatorViewModel.clearResult()

            var isInputComplete = bmiCalculatorViewModel.validateInput(inputWeight, inputHeight)
            if (isInputComplete) {
                bmiCalculatorViewModel.calculateBMI(inputWeight, inputHeight)
            }
        }
    }
}