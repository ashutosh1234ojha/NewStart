package com.example.newstart.pagination.network

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ashutosh Ojha on 10,April,2022
 */
interface RetroService {


    @GET("character")
    suspend fun getDataFromAPI(@Query("page") query: Int):RickAndMortyList
}