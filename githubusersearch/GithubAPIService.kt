package com.example.githubusersearch

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type

interface GithubAPIService {
    @GET("/users/{userId}") //https://api.github.com
    fun getUser(
        @Path("userId") id: String
    )
}

data class GithubUser(val id: Int, val login: String)

class GithubUserDeserializer : JsonDeserializer<GithubUser> {

}