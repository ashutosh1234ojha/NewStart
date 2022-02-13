package com.example.newstart.cache.feature.restaurant

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstart.cache.api.RestaurantApi
import com.example.newstart.cache.data.RestaurantRepository
import com.example.newstart.cache.util.Resource
import com.example.newstart.databinding.ActivityRestaurantBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Ashutosh Ojha on 30,January,2022
 */
@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {
//     val  mainViewModel: RestaurantViewModel by viewModels()
//private val mainViewModel: RestaurantViewModel by lazy {
//    this?.run {
//        ViewModelProviders.of(this, mainViewModelFactory)
//            .get(RestaurantViewModel::class.java)
//    } ?: throw Exception("Invalid Activity")
//}

    @Inject
    lateinit var restaurantApi: RestaurantApi

    @Inject
    lateinit var repository: RestaurantRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        private val mainViewModelFactory: MainViewModelFactory by instance()

        val mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(repository)
        ).get(RestaurantViewModel::class.java)
        val restaurantAdapter = RestaurantAdapter()
        Log.d("RestaurantAdapter", restaurantApi.toString())
        binding.apply {

            binding.rvRestaurant.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@RestaurantActivity)
            }

            mainViewModel.restaurant.observe(this@RestaurantActivity, Observer { result ->
                restaurantAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                tvError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()

                tvError.text = result.error?.localizedMessage
            })
        }

    }


}
