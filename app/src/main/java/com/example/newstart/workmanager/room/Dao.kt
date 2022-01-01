package com.example.newstart.workmanager.room

import androidx.room.Delete
import androidx.room.Insert

import androidx.room.Update
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query


/**
 * Created by Ashutosh Ojha on 01,December,2021
 */
@Dao
interface Dao {
    // below method is use to
    // add data to database.
    @Insert
    fun insert(model: CourseModal?)

    // below method is use to update
    // the data in our database.
    @Update
    fun update(model: CourseModal?)

    // below line is use to delete a
    // specific course in our database.
    @Query("SELECT * FROM course_table")
    open fun getAllCourses(): List<CourseModal>
}