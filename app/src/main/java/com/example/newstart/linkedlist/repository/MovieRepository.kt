package com.example.newstart.linkedlist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newstart.mvvm.models.MovieModel
import com.example.newstart.mvvm.requests.MovieApiClient

/**
 * Created by Ashutosh Ojha on 28,January,2022
 */
object MovieRepository  {
    private val movieMutableLiveData = MutableLiveData<List<MovieModel>>()
    var movieLiveData: LiveData<List<MovieModel>> = movieMutableLiveData

    fun  ab(){
        MovieApiClient.movieLiveData
    }




}