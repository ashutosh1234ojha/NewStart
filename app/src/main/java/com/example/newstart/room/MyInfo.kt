package com.example.newstart.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date_time")
data class MyInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "date") var title: String,
    @ColumnInfo(name = "content") var content: String
)
