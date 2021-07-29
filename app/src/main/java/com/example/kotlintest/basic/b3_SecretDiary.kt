package com.example.kotlintest.basic

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit
import com.example.kotlintest.R

class b3_SecretDiary : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val openButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openButton)
    }
    private val changePasswordButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }

    private var changePasswordMode = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b3_secret_diary)

        // lazy하게 선언했기 때문에 init되는 시점이 numberpicker를 사용하는 시점
        // 따라서, 호출.
        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {

            if (changePasswordMode){
                Toast.makeText(this, "비밀번호 변경 중입니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE);
            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                // 패스워드 성공
                startActivity(Intent(this, b3_DiaryActivity::class.java))
            } else {

                showErrorAlertDialog()
            }

        }

        changePasswordButton.setOnClickListener{
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE);
            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
            if(changePasswordMode){
                //번호를 저장하는 기능

                //* ktx - edit사용 간단.
                // (1)
//                passwordPreferences.edit{
//                    val passwordFromUser =
//                        "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
//                    putString("password", passwordFromUser)
//                    commit()
//                }

                // (2)
                passwordPreferences.edit(true){

                    putString("password", passwordFromUser)

                }
                changePasswordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)



            }else{
                // 활성화:: 비밀번호가 맞는지를 체크
                // private - 다른 앱들과 공유하지 않고 앱 내에서만 사용

                if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                    changePasswordMode = true
                    Toast.makeText(this, "변경할 패스워드를 입력해주세요", Toast.LENGTH_LONG).show()
                    changePasswordButton.setBackgroundColor(Color.RED)
                } else {

                    showErrorAlertDialog()
                }
            }
        }

    }

    private fun showErrorAlertDialog(){
        AlertDialog.Builder(this)
            .setTitle("실패!!")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("확인") { _, _ -> }
            .create()
            .show()
    }
}