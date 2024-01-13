package com.example.newstart.syncWithServer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var fName: String,
    var lName: String,
    var date: String,
    var isSynced: Int = 0
)
