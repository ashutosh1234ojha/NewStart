package com.example.newstart.mvvm.requests

import com.example.newstart.mvvm.utils.Credentials
import com.example.newstart.mvvm.utils.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Ashutosh Ojha on 27,January,2022
 */
object Services {
    private var retrofit = Retrofit.Builder()
        .baseUrl(Credentials.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var movieApi: MovieApi = retrofit.create(MovieApi::class.java)

    fun getMovieApi() = movieApi
}