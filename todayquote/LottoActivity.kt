package com.example.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class LottoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)

//       var lottoNum = findViewById<TextView>(R.id.lottoNum)
//        var n1 = (1..45).random()
//        var n2 = (1..45).random()
//        var n3 = (1..45).random()
//        var n4 = (1..45).random()
//        var n5 = (1..45).random()
//        var n6 = (1..45).random()
//        lottoNum.text = "${n1} - ${n2} - ${n3} - ${n4} - ${n5} - ${n6}"

        var lottoNum = mutableListOf<Int>()
        for(i in 1..6) lottoNum.add((1..45).random())
        //lottoNum.text = "${lottoNum[0]}-${lottoNum[1]}"

    }
}