package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInput = findViewById<EditText>(R.id.user_id_input)
        val content = findViewById<Button>(R.id.input_btn)


            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(
                    GsonConverterFactory.create(
                        /*
                        GsonBuilder().registerTypeAdapter(
                            GithubUser::class.java,
                            GithubUserDeserializer()
                        ).create()
                         */
                    )
                ).build()

        val apiService = retrofit.create(GithubAPIService::class.java)

        findViewById<Button>(R.id.input_btn).setOnClickListener{
            val id = userInput.text.toString()
            val apiCallForData = apiService.getUser(id, "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")
            apiCallForData.enqueue(object : Callback<GithubUser>{
                override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                    val data = response.body()!!
                    Log.d("mytag", data.toString())

                    content.text = "login : ${data.login}\n id : ${data.id}"
                }

                override fun onFailure(call: Call<GithubUser>, t: Throwable) {

                }

            })
            }
        }
    }