package com.example.lotteryapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var currentNums: String    //lateinit => 널러블 타입을 안 해도 됨! 초기화 안해도 괜춘

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //shared preference
        val pref = getSharedPreferences("lotto_nums",
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()

        //미리 생성
        var nums = findViewById<TextView>(R.id.lotto_num)
        currentNums = generateRandomLottoNum(6, " - ", true)
        nums.text = currentNums // 생성된것 currenNums에 저장

        //생성
        var create_btn = findViewById<Button>(R.id.lotto_btn)
        create_btn.setOnClickListener {
            currentNums = generateRandomLottoNum(6, " - ", true)
            nums.text = currentNums // 생성된것 currenNums에 저장
        }

        //4. 저장된 번호 확인
        var checkNum = findViewById<Button>(R.id.check_num)
        checkNum.setOnClickListener {
            val intent = Intent(this, CheckNumberActivity::class.java)
            startActivity(intent)
        }

        /*
        * 처음에 프리퍼런스에서 "lottonums" 키를 통해서 문자열 값을 가져오는데 맨 처음에는 저장된게 없으므로 두 번째
        * 인자값인 빈 문자열을 가져옴
        * val lottoNums = pref.getString("lottonums", "")
        * 그리고 이후 if, else 표현식을 통해서 빈 문자열인 경우에는 빈 리스트를 하나 생성하도록 하고, 그 리스트에
        * 현재 로또 번호 저장
        * 이후
        * val lottoNums = pref.getString("lottonums", "")
        * 가 실행될 때에는 저장된 번호가 있으므로 "1-2-3-4-5-6" 와 같은 값 리턴
        * else 쪽으로 빠져서 ["1-2-3-4-5-6"] 내용을 담은 리스트 반환
        * 매번 저장할 때마다 joinToString을 통해서 문자열로 바꿔서 저장하므로 실제 저장되는 내용은
        * "1-2-3-4-5-6,6-5-4-3-2-1" <=이런 문자열이 저장이 됨
        */
        //저장
        var save_btn = findViewById<Button>(R.id.save_btn)
        save_btn.setOnClickListener {
            var lottoNums = pref.getString("lottonums", "")   //키가 없으면 빈 문자열 가져옴

            var list = if(lottoNums == ""){ //if문 마지막 구문이 대입이 됨 => lottoNums에 값이 없으면 빈 값 리턴
                mutableListOf<String>()
            }else{
                lottoNums!!.split(",").toMutableList()  // !!는 무조건 값이 있다는 뜻
            }

            list.add(currentNums)
            editor.putString("lottonums", list.joinToString(",")).apply()

            Log.d("mytag", list.toString())

        }

    }
    private fun generateRandomLottoNum(count: Int, adapter: String, overlap: Boolean) : String {

        if(overlap === false){  // 중복 허용x
            val mlist = mutableSetOf<Int>()

            for(i in 1.. count){
                mlist.add((1..45).random())
            }
            return mlist.joinToString(adapter)
        }
        else{
            val mlist = mutableListOf<Int>()


            for(i in 1.. count){
                mlist.add((1..45).random())
            }
            return mlist.joinToString(adapter)
        }
        return ""

        // Log.d("mytag", nums.text.toString())

    }

}