package com.example.newstart.cache.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ashutosh Ojha on 30,January,2022
 */
@Entity(tableName = "restaurant")
data class Restaurant(@PrimaryKey val name: String , val type: String, val logo: String, val address: String)