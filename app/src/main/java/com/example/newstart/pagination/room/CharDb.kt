package com.example.newstart.pagination.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newstart.pagination.network.CharacterData

/**
 * Created by Ashutosh Ojha on 12,April,2022
 */
@Database(entities = [CharacterData::class], version = 1)
abstract class CharDb : RoomDatabase() {
    abstract fun userDao(): CharDao

    companion object{
        fun getInstance(applicationContext: Application) = Room.databaseBuilder(
            applicationContext,
            CharDb::class.java, "database-name"
        ).build()
    }


}