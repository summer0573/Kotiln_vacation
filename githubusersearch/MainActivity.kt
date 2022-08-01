package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInput = findViewById<EditText>(R.id.user_id_input)

        findViewById<Button>(R.id.input_btn).setOnClickListener{
            val id = userInput.text.toString()
            //val apiCallForData


        }
    }
}