package com.example.memes.webservice


import com.example.memes.data.Meme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("https://meme-api.herokuapp.com/gimme/")
    fun getUsers(@Query("api_key") key: String): Call<Meme>
}