package com.example.todayquote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class calculator_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_activity)

        val num1 = findViewById<EditText>(R.id.number1)
        val num2 = findViewById<EditText>(R.id.number2)
        val operator = findViewById<EditText>(R.id.operator)
        val calBut = findViewById<Button>(R.id.button)

        calBut.setOnClickListener{
            Log.d("mytag", num1.text.toString())
            Log.d("mytag", num2.text.toString())
            Log.d("mytag", operator.text.toString())

            val num11 = num1.text.toString().toInt()
            val num22 = num2.text.toString().toInt()

            val intent = Intent(this, CalculateResultActivity::class.java)
            intent.putExtra("num1", num11)
            intent.putExtra("num2", num22)
            intent.putExtra("operator", operator.text.toString())
            startActivity(intent)
        }


    }
}