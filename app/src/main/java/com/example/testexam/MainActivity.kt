package com.example.testexam

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
const val INTERPRETATION="interpretation"
const val BMI="bmi"
const val WEIGHT_VALUE="weightvalue"
const val HEIGHT_VALUE="heightvalue"
const val SHARED_PREFS="mysharedpref"
class MainActivity : AppCompatActivity() {
    private lateinit var etweight:EditText
    private lateinit var etheight:EditText
    private lateinit var btnCalculebmi:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //initialiser
        etweight=findViewById(R.id.etWeight)
        etheight=findViewById(R.id.etHeight)
        btnCalculebmi=findViewById(R.id.button)
        loadData()
        btnCalculebmi.setOnClickListener {
            calculateBMI()
        }
    }
    private fun save(){
        val sharedPref=this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor=sharedPref.edit()
        editor.putString(WEIGHT_VALUE,etweight.text.toString())
        editor.putString(HEIGHT_VALUE,etheight.text.toString())
        editor.apply()
    }
    private fun loadData() {
        val sharedPref=this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val height=sharedPref.getString(HEIGHT_VALUE,"")
        val weight=sharedPref.getString(WEIGHT_VALUE,"")
        etweight.setText(weight)
        etheight.setText(height)
    }
    private fun calculateBMI() {
        val weight=etweight.text.toString().toDoubleOrNull()
        val height=etheight.text.toString().toDoubleOrNull()
        if (weight != null && height != null && height > 0.5 && height < 3 && weight > 0) {
            val bmi = weight / (height * height)
            val bmiFormated=String.format("%.2f",bmi)
            val interpretation=when{
                bmi < 18.5 -> "Underweight"
                bmi in 18.5..24.9 -> "Normal weight"
                bmi in 25.0..29.9 -> "Overweight"
                else -> "Obesity"
            }
            save()

        val intent=Intent(this,ResultatActivity::class.java).apply {
            putExtra(BMI,"votre bmi :$bmiFormated")
            putExtra(INTERPRETATION,interpretation)
        }
            startActivity(intent)
    }
        else{
            Toast.makeText(this,"entre propre value ",Toast.LENGTH_SHORT).show()
        }
    }
}