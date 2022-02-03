package com.example.newstart.cache.data

import com.example.newstart.cache.api.RestaurantApi
import com.example.newstart.cache.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Created by Ashutosh Ojha on 02,February,2022
 */
class RestaurantRepository @Inject constructor(
    private val restaurantApi: RestaurantApi,
    private val database: RestaurantDatabase
) {
    private val dao = database.restaurantDao()

    fun getRestaurant() = networkBoundResource(query = { dao.getAllRestaurants() }, {
        delay(2000)
        restaurantApi.getRestaurants()
    },
        { restaurants ->

            dao.deleteAllRestaurants()
            dao.insertRestaurants(restaurants)
        })
}