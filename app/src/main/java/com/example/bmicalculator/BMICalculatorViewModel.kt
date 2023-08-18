package com.example.bmicalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class BMICalculatorViewModel() : ViewModel() {
    var result = ""
    var resultCategory = ""
    var weightWarning = ""
    var heightWarning = ""

    private val _currentResult = MutableLiveData<String>()
    fun currentResult(): LiveData<String> = _currentResult

    private val _currentResultCategory = MutableLiveData<String>()
    fun currentResultCategory(): LiveData<String> = _currentResultCategory

    private val _currentWeightWarning = MutableLiveData<String>()
    fun currentWeightWarning(): LiveData<String> = _currentWeightWarning

    private val _currentHeightWarning = MutableLiveData<String>()
    fun currentHeightWarning(): LiveData<String> = _currentHeightWarning

    fun clearWarning(){
        weightWarning = ""
        heightWarning = ""
        _currentWeightWarning.value = weightWarning
        _currentHeightWarning.value = heightWarning
    }

    fun clearResult(){
        result = ""
        resultCategory = ""
        _currentResult.value = result
        _currentResultCategory.value = result
    }

    fun validateInput(inputWeight: String, inputHeight: String): Boolean{
        var isInputComplete = true


        if (inputWeight.isEmpty()) {
            isInputComplete = false
            weightWarning = "Field ini tidak boleh kosong"
            _currentWeightWarning.value = weightWarning
        }
        if (inputHeight.isEmpty()) {
            isInputComplete = false
            heightWarning = "Field ini tidak boleh kosong"
            _currentHeightWarning.value = heightWarning
        }

        return isInputComplete
    }


    fun calculateBMI(inputWeight: String, inputHeight: String){


        val heightInMeter = inputHeight.toDouble()/100
        val bmi = inputWeight.toDouble() / Math.pow(heightInMeter, 2.0)
        result = DecimalFormat("#.##").format(bmi).toString()
        if(bmi < 18.5){
            resultCategory = BMICategory.KURUS.toString()
        }else if (bmi < 25 && bmi >= 18.5){
            resultCategory = BMICategory.NORMAL.toString()
        }else if (bmi >= 25 && bmi < 30){
            resultCategory = BMICategory.GEMUK.toString()
        } else {
            resultCategory = BMICategory.OBESITAS.toString()
        }

        _currentResult.value = result
        _currentResultCategory.value = resultCategory

    }
}