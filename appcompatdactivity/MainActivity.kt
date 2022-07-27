package com.example.appcompatdactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherdustchecker.WeatherPageFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //상단 제목 표시줄 숨기기
        supportActionBar?.hide()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container,
            WeatherPageFragment.newInstance(37.58, 126.98))
        transaction.commit()


    }
}