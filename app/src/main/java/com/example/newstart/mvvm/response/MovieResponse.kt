package com.example.newstart.mvvm.response

import com.example.newstart.mvvm.models.MovieModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ashutosh Ojha on 27,January,2022
 */
//Single movie response
data class MovieResponse(@SerializedName("results") @Expose val movieModel: MovieModel)