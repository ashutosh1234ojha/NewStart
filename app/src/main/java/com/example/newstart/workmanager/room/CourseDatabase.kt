package com.example.newstart.workmanager.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Created by Ashutosh Ojha on 01,December,2021
 */
@Database(entities = [CourseModal::class], version = 1)
abstract class CourseDatabase : RoomDatabase() {
    // below line is to create instance
    // for our database class.

    // below line is to create
    // abstract variable for dao.
    abstract fun Dao(): Dao?

    companion object{
        private var instance: CourseDatabase? = null

        @Synchronized
        open fun getInstance(context: Context): CourseDatabase? {
            // below line is to check if
            // the instance is null or not.
            if (instance == null) {
                // if the instance is null we
                // are creating a new instance
                instance =  // for creating a instance for our database
                        // we are creating a database builder and passing
                        // our database class with our database name.
                    Room.databaseBuilder(
                        context.getApplicationContext(),
                        CourseDatabase::class.java, "course_database"
                    ) // below line is use to add fall back to
                        // destructive migration to our database.
                        .fallbackToDestructiveMigration() // below line is to add callback
                        .allowMainThreadQueries()
                        // to our database.
                        //  .addCallback(roomCallback) // below line is to
                        // build our database.
                        .build()
            }
            // after creating an instance
            // we are returning our instance
            return instance
        }
    }



    // below line is to create a callback for our room database.

}