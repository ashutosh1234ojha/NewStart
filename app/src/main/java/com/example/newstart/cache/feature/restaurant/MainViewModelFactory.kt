package com.example.newstart.cache.feature.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newstart.cache.data.RestaurantRepository

/**
 * Created by Ashutosh Ojha on 01,February,2022
 */
class MainViewModelFactory (private val restaurantRepository: RestaurantRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantViewModel::class.java)) {
            return RestaurantViewModel(restaurantRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}