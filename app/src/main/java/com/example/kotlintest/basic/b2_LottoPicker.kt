package com.example.kotlintest.basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.kotlintest.R

class b2_LottoPicker : AppCompatActivity() {

    private val clearButton: Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }
    private val addButton: Button by lazy {
        findViewById<Button>(R.id.addButton)
    }
    private val runButton: Button by lazy {
        findViewById<Button>(R.id.runButton)
    }

    private val numberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val numberTextViewList: List<TextView> by lazy {
        listOf<TextView>(
            findViewById<TextView>(R.id.tv1),
            findViewById<TextView>(R.id.tv2),
            findViewById<TextView>(R.id.tv3),
            findViewById<TextView>(R.id.tv4),
            findViewById<TextView>(R.id.tv5),
            findViewById<TextView>(R.id.tv6)
        )
    }

    private var didRun = false

    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b2_lotto_picker)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddbutton()
        initClearButton()
    }

    private fun initRunButton() {
        runButton.setOnClickListener {
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed{ index, number ->
                val textView = numberTextViewList[index]

                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number, textView)
            }

            Log.d("MainActivity", list.toString())
        }
    }

    private fun getRandomNumber() : List<Int> {
        val numberList = mutableListOf<Int>()
            .apply {
                for (i in 1..45) {
                    if(pickNumberSet.contains(i)){
                        continue
                    }
                    this.add(i)
                }
            }

        numberList.shuffle()

        val newList = pickNumberSet.toList() + numberList.subList(0, 6-pickNumberSet.size)

        return newList.sorted()
    }



    private fun initAddbutton(){
        addButton.setOnClickListener{

            if(didRun){
                Toast.makeText(this, "????????? ?????? ??????????????????. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(pickNumberSet.size >= 5){
                Toast.makeText(this, "????????? 5???????????? ????????? ??? ????????????. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(pickNumberSet.contains(numberPicker.value)){
                Toast.makeText(this, "?????? ????????? ???????????????. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            setNumberBackground(numberPicker.value, textView);

            pickNumberSet.add(numberPicker.value)
        }
    }
    private fun setNumberBackground(number:Int, textView: TextView){
        when(number){
            in 1..10 ->textView.background = ContextCompat.getDrawable(this,
                R.drawable.circle_yellow
            );
            in 11..20 ->textView.background = ContextCompat.getDrawable(this,
                R.drawable.circle_blue
            );
            in 21..30 ->textView.background = ContextCompat.getDrawable(this,
                R.drawable.circle_red
            );
            in 31..40 ->textView.background = ContextCompat.getDrawable(this,
                R.drawable.circle_gray
            );
            else ->textView.background = ContextCompat.getDrawable(this,
                R.drawable.cricle_green
            );
        }
    }
    private fun initClearButton(){
        clearButton.setOnClickListener{
            pickNumberSet.clear()
            numberTextViewList.forEach{
                it.isVisible = false
            }
            didRun = false
        }
    }
}