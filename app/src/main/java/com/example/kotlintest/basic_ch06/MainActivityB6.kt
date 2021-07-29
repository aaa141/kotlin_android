package com.example.kotlintest.basic_ch06

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.TextView
import com.example.kotlintest.R

class MainActivityB6 : AppCompatActivity() {

    private val remainMinutesTextView: TextView by lazy {
        findViewById<TextView>(R.id.remainMinutesTextView)
    }
    private val remainSecondsTextView: TextView by lazy {
        findViewById<TextView>(R.id.remianSecondsTextView)
    }
    private val seekBar: SeekBar by lazy {
        findViewById<SeekBar>(R.id.seekBar)
    }

    private var currentCountDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_b6)

        bindViews()
    }

    private fun bindViews() {
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if(fromUser) {
                        updateRemainTime(progress * 60 * 1000L)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    currentCountDownTimer?.cancel() // null일 경우 멈춤
                    currentCountDownTimer = null // 로직을 확인할 때 의미상으로 명확하게.
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    seekBar ?: return  // seekBar가 null인 경우 실행하지 않음
                    currentCountDownTimer =
                        createCountDownTimer(seekBar.progress * 60 * 1000L).start()
                    currentCountDownTimer?.start()
                }

            }
        )
    }

    private fun createCountDownTimer(initialMillis: Long) =
        object : CountDownTimer(initialMillis, 1000L) {


            override fun onTick(millisUntilFinished: Long) {
                updateRemainTime(millisUntilFinished)
                updateSeekBar(millisUntilFinished)
            }

            override fun onFinish() {
                updateRemainTime(0)
                updateSeekBar(0)
            }
        }


    private fun updateRemainTime(remainMillis: Long) {

        val remainSeconds = remainMillis / 1000
        remainMinutesTextView.text = "%02d".format(remainMillis / 60)
        remainSecondsTextView.text = "%02d".format(remainSeconds % 60)
    }

    private fun updateSeekBar(remainMillis: Long) {
        seekBar.progress = (remainMillis / 1000 / 60).toInt()
    }
}
