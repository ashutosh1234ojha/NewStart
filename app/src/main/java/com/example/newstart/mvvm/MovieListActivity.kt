package com.example.newstart.mvvm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.newstart.HelloWorld
import com.example.newstart.R
import com.example.newstart.mvvm.models.MovieModel
import com.example.newstart.mvvm.requests.Services
import com.example.newstart.mvvm.response.MovieSearchResponse
import com.example.newstart.mvvm.utils.Credentials
import com.example.newstart.mvvm.viewmodel.MovieListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ashutosh Ojha on 27,January,2022
 */
class MovieListActivity : AppCompatActivity() {
    val movieListViewModel:MovieListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activivty_movie_list)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
//            searchMovie()
          //  getMovie()
            val hw= HelloWorld()
            HelloWorld.find(4)
        }
    }

    fun observerAnyChange(){
        movieListViewModel.movieLiveData.observe(this, Observer {

        })
    }

    private fun searchMovie() {
        val movieApi: Call<MovieSearchResponse> =
            Services.getMovieApi().searchMovie(Credentials.API_KEY, "Action", "1")

        movieApi.enqueue(object : Callback<MovieSearchResponse> {
            override fun onResponse(
                call: Call<MovieSearchResponse>,
                response: Response<MovieSearchResponse>
            ) {
                if (response.code() == 200) {
                    Log.d("MovieApi", response.body().toString())
                }
            }

            override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
            }

        })
    }

    private fun getMovie() {
        val movieApi: Call<MovieModel> =
            Services.getMovieApi().getMovie(550, Credentials.API_KEY)

        movieApi.enqueue(object : Callback<MovieModel> {
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                if (response.code() == 200) {
                    Log.d("getMovie", response.body().toString())
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
            }

        })
    }
}