package com.example.newstart.cache.api

import com.example.newstart.cache.data.Restaurant
import retrofit2.http.GET

/**
 * Created by Ashutosh Ojha on 31,January,2022
 */
interface RestaurantApi {

    companion object{
        const val BASE_URL = "https://random-data-api.com/api/"
    }
    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants(): List<Restaurant>
}