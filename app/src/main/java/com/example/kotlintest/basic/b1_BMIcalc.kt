package com.example.kotlintest.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kotlintest.R

class b1_BMIcalc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b1_bmi_calc)

        //val heightEditText: EditText? //?을 붙이면 null이 될 수 있다
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val weightEditText = findViewById<EditText>(R.id.weightEditText)

        val resultButton = findViewById<Button>(R.id.resultButton)

        resultButton.setOnClickListener {

            if (heightEditText.text.isEmpty() || weightEditText.text.isEmpty()) {
                Toast.makeText(this, "빈 값이 있습닌다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener  // 어디서 return되는지 명시
            }
            val height: Int = heightEditText.text.toString().toInt()
            val weight: Int = weightEditText.text.toString().toInt()
            Log.d("MainActivity", "height: $height weight: $weight")

            val intent = Intent(this, b1_ResultActivity::class.java)

            intent.putExtra("height", height)
            intent.putExtra("weight",weight)
            startActivity(intent)
        }
    }
}