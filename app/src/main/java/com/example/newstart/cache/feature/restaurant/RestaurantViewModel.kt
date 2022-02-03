package com.example.newstart.cache.feature.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.newstart.cache.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ashutosh Ojha on 31,January,2022
 */
@HiltViewModel
class RestaurantViewModel @Inject constructor(repository: RestaurantRepository) : ViewModel() {


    //        private val restaurantLiveData = MutableLiveData<List<Restaurant>>()
//    val restaurant: LiveData<List<Restaurant>> = restaurantLiveData
//
//
//    init {
//        viewModelScope.launch {
//            val restaurant = api.getRestaurants()
//            delay(2000) //Just for testing purpose, to see the progress bar
//            restaurantLiveData.value = restaurant
//        }
//    }
    val restaurant = repository.getRestaurant().asLiveData()
}