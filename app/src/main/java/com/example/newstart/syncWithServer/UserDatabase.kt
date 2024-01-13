package com.example.newstart.syncWithServer

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [UserData::class], version = 1)
abstract class UserDatabase:RoomDatabase() {
    abstract fun useDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): UserDatabase {
            if(instance == null)
                instance = databaseBuilder(ctx.applicationContext, UserDatabase::class.java,
                    "user_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            return instance!!

        }



    }

}