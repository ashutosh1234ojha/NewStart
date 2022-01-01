package com.example.newstart.workmanager.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ashutosh Ojha on 01,December,2021
 */
@Entity(tableName = "course_table")
data class CourseModal(var time:String, @PrimaryKey(autoGenerate = true) val uid: Int=0,
)
