package com.example.newstart.mvvm.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newstart.mvvm.models.MovieModel

/**
 * Created by Ashutosh Ojha on 28,January,2022
 */
object MovieApiClient {
    private val movieMutableLiveData = MutableLiveData<List<MovieModel>>()
    var movieLiveData: LiveData<List<MovieModel>> = movieMutableLiveData
}