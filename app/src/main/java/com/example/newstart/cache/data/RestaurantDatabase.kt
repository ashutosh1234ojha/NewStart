package com.example.newstart.cache.data

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * Created by Ashutosh Ojha on 02,February,2022
 */
@Database(entities = [Restaurant::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}