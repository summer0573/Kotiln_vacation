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
    @GET("/users/{userId}")
    fun getUser(
        @Path("userId") id: String
    ) : Call<GithubUser>
}

data class GithubUser(val id: Int, val login: String)

class GithubUserDeserializer : JsonDeserializer<GithubUser> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GithubUser {
        val root = json?.getAsJsonObject()
        val id = root?.getAsJsonPrimitive("id")?.asInt
        val login = root?.getAsJsonPrimitive("login")?.asString

        return GithubUser(id!!, login!!)
    }

}