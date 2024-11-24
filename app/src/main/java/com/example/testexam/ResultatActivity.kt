package com.example.testexam

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultatActivity : AppCompatActivity() {
    private lateinit var tvBMI: TextView
    private lateinit var tvInterpretation: TextView
    private lateinit var btnReturn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultat)

        tvBMI=findViewById(R.id.tvBMI)
        tvInterpretation=findViewById(R.id.textView2)
        btnReturn=findViewById(R.id.btnReturn)

        tvBMI.text=intent.getStringExtra(BMI)
        tvInterpretation.text=intent.getStringExtra(INTERPRETATION)

        btnReturn.setOnClickListener{
            finish()
        }

    }
}