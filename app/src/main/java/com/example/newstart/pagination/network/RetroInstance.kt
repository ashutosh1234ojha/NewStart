package com.example.newstart.pagination.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ashutosh Ojha on 10,April,2022
 */
class RetroInstance {

    companion object{
        val baseURL = "https://rickandmortyapi.com/api/"

        fun getRetroInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}