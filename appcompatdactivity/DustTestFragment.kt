package com.example.appcompatdactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherdustchecker.MyDeserialize
import com.example.weatherdustchecker.WeatherPageFragment
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import wikibook.learnandroid.weatherdustchecker.APICall
import java.net.URL

@JsonDeserialize(using= MyDust::class)

data class OpenDustAPI(val pm10 : Double, val pm25 : Double)

class MyDust : StdDeserializer<OpenDustAPI>( //미세먼지 데이터 가져오기
    OpenDustAPI :: class.java
) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?)
    : OpenDustAPI {
        val node = p?.codec?.readTree<JsonNode>(p)

        val data = node?.get("data")
        val iaqi = data?.get("iaqi")
        val pm10 = iaqi?.get("pm10")?.get("v")?.asDouble()
        val pm25 = iaqi?.get("pm25")?.get("v")?.asDouble()

        return OpenDustAPI(pm10!!, pm25!!)

    }
}


class DustTestFragment : Fragment(){
    lateinit var dustImage : ImageView
    lateinit var choDustnum : TextView
    lateinit var normalDustnum : TextView

    val APP_ID = "667ab304eef4032073f7d0faecd7179a3c2ac2d9"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater
            .inflate(
                R.layout.dustext_fragment,
                container, false)

        dustImage = view.findViewById<ImageView>(R.id.dust_icon)
        choDustnum = view.findViewById<TextView>(R.id.cho_dust_num)
        normalDustnum = view.findViewById<TextView>(R.id.normal_dust_num)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lat = arguments?.getDouble("lat")
        val lon = arguments?.getDouble("lon")

        val url = "https://api.waqi.info/feed/geo:${lat};${lon}/?token=${APP_ID}"

        APICall(object : APICall.APICallback{
            override fun onComplete(result: String) {
                Log.d("mytag", result)
                val mapper = jacksonObjectMapper()
                var data = mapper?.readValue<OpenDustAPI>(result)
                normalDustnum.text = data.pm10.toString()
                choDustnum.text = data.pm25.toString()

                val normalDustStatus = view.findViewById<TextView>(R.id.normal_dust_status)
                var dust = "(미세먼지)" //미세먼지 구분
                val pm10 = data.pm10.toInt()
                if(0 <= pm10 && 50 >= pm10){ //미세먼지 값 계산하기 및 사진 넣기
                    normalDustStatus.text = "좋음${dust}"
                    dustImage.setImageResource(R.drawable.good)
                } else if (51 <= pm10 && 150 >= pm10) {
                    normalDustStatus.text = "보통${dust}"
                    dustImage.setImageResource(R.drawable.normal)
                } else if (151 <= pm10 && 300 >= pm10) {
                    normalDustStatus.text = "나쁨${dust}"
                    dustImage.setImageResource(R.drawable.bad)
                } else if (301 <= pm10){
                    normalDustStatus.text = "매우 나쁨${dust}"
                    dustImage.setImageResource(R.drawable.very_bad)
                }

                val choDustStatus = view.findViewById<TextView>(R.id.cho_dust_status)
                dust = "(초미세먼지)"
                val pm25 = data.pm25.toInt()
                if(0 <= pm25 && 70 >= pm25){//초 미세먼지 값 계산하기
                    choDustStatus.text = "좋음${dust}"
                } else if (71 <= pm25 && 170 >= pm25) {
                    choDustStatus.text = "보통${dust}"
                } else if (171 <= pm25 && 350 >= pm25) {
                    choDustStatus.text = "나쁨${dust}"
                } else if (351 <= pm25){
                    choDustStatus.text = "매우 나쁨${dust}"
                }

            }
        }).execute(URL(url))



    }

    companion object {
        fun newInstance(lat: Double, lon: Double)
                : DustTestFragment {

            val fragment = DustTestFragment()

            val args = Bundle()
            args.putDouble("lat", lat)
            args.putDouble("lon", lon)
            fragment.arguments = args

            return fragment
        }
    }

}