package com.example.appcompatdactivity

import retrofit2.http.GET

interface DustAPIService {
    @GET("")
    fun getDustStatusInfo(

    )
}