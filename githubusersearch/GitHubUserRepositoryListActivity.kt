package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubUserRepositoryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_user_repository_list)

        val id = intent.getStringExtra("userId")!!
        Log.d("mytag", id)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(GitHubAPIService::class.java)
        val apiCallForData = apiService.getRepos(id,
            "token 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")
    }
}