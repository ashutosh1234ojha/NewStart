package com.example.newstart.cache.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ashutosh Ojha on 02,February,2022
 */
@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurantList: List<Restaurant>)

    @Query("DELETE FROM restaurant")
    suspend fun deleteAllRestaurants()

    @Query("SELECT * FROM restaurant")
    fun getAllRestaurants(): Flow<List<Restaurant>>
}