package com.example.newstart.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newstart.mvvm.models.MovieModel

/**
 * Created by Ashutosh Ojha on 28,January,2022
 */
class MovieListViewModel : ViewModel() {
    private val movieMutableLiveData = MutableLiveData<List<MovieModel>>()
    var movieLiveData: LiveData<List<MovieModel>> = movieMutableLiveData
}