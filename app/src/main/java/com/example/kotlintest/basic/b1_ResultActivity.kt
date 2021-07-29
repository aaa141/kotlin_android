package com.example.kotlintest.basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.kotlintest.R
import kotlin.math.pow

class b1_ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b1_result)

        val height = intent.getIntExtra("height", 0);
        val weight = intent.getIntExtra("weight", 0);

        Log.d("ResultActivity","height: $height weigth: $weight")

        val bmi = weight / (height / 100.0).pow(2.0) // pow는 인자로 double형을 받음
        val resultText = when{
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중정 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상체중"
            else -> "저체중"
        }

        val resultValueTextView = findViewById<TextView>(R.id.resultValueTextView)
        val resultStringTextView = findViewById<TextView>(R.id.resultStringTextView)

        resultValueTextView.text = bmi.toString()
        resultStringTextView.text = resultText
    }
}