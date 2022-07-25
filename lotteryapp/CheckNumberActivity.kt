package com.example.lotteryapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_number_activity)
        val pref = getSharedPreferences("lotto_nums", Context.MODE_PRIVATE)
        var lottoNums = pref.getString("lottonums", "")
        var numlist = if(lottoNums == ""){ //if문 마지막 구문이 대입이 됨 => lottoNums에 값이 없으면 빈 값 리턴
            mutableListOf<String>()
        }else{
            lottoNums!!.split(",").toMutableList()  // !!는 무조건 값이 있다는 뜻
        }

        val listView = findViewById<RecyclerView>(R.id.num_list)
        listView.setHasFixedSize(true)
        //준비물 > 레이아웃 매니저
        listView.layoutManager = LinearLayoutManager(this)

        listView.adapter = lottolyListAdapter(numlist)

        listView.setHasFixedSize(true)


    }
}