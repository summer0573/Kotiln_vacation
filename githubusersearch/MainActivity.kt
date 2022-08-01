package com.example.githubusersearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
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
        val content = findViewById<TextView>(R.id.Github_view)
        val profile = findViewById<ImageView>(R.id.profile_image)


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
            val apiCallForData = apiService.getUser(id,"token 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")
            apiCallForData.enqueue(object : Callback<GithubUser>{
                override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                    //Log.d("mytag", response.code().toString())
                    //Log.d("mytag", data.toString())
                    val code : Int = response.code()
                    if (code.toString().startsWith("4")) {
                        Toast.makeText(applicationContext, "유저가 없습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        val data = response.body()!!
                        content.text =
                            "login : ${data.login} \n id : ${data.id} \n name : ${data.name} \n followers : ${data.followers} \n following : ${data.following}"
                        Glide.with(this@MainActivity)
                            .load(data.avatar_url)
                            .into(profile);
                    }
                }
                override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                }
            })
            }
        val userButton = findViewById<Button>(R.id.user_btn)
        userButton.setOnClickListener{
            val intent = Intent(this, GitHubUserRepositoryListActivity::class.java)
            //intent.putExtra("userInput")
            startActivity(intent)
        }
        }
    }