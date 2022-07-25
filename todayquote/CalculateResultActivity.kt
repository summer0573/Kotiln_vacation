package com.example.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CalculateResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_result)

        val num1 = intent.getIntExtra("num1", -1)
        val num2 = intent.getIntExtra("num2", -1)
        val op = intent.getStringExtra("operator")!!

        Log.d("my_tag", num1.toString())
        Log.d("my_tag", num2.toString())
        Log.d("my_tag", op)

        val result = findViewById<TextView>(R.id.result)

        if(op == "+") result.text = (num1 + num2).toString()
        else if(op == "-") result.text = (num1 - num2).toString()
        else if(op == "*") result.text = (num1 * num2).toString()
        else if(op == "%") result.text = (num1 % num2).toString()
        else {
            Toast.makeText(this, "올바른 연산자를 입력해주세요.", Toast.LENGTH_SHORT).show()
            finish()
        }
        val quit = findViewById<Button>(R.id.quit)
        quit.setOnClickListener{
            finish()
        }


    }
}