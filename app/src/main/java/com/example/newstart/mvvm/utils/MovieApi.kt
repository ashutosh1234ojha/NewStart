package com.example.newstart.mvvm.utils

import com.example.newstart.mvvm.models.MovieModel
import com.example.newstart.mvvm.response.MovieSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Ashutosh Ojha on 27,January,2022
 */
interface MovieApi {
    @GET("/3/search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: String
    ): Call<MovieSearchResponse>

//    https://api.themoviedb.org/3/movie/550?api_key=bbb85f4592527fbd76a127bcf8a40b11

    @GET("/3/movie/{movie_id}?")
    fun getMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
    ): Call<MovieModel>


}