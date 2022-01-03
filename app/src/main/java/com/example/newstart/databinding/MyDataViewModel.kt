package com.example.newstart.databinding

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * Created by Ashutosh Ojha on 27,December,2021
 */
class MyDataViewModel(application: Application) : AndroidViewModel(application) {
    private var user: User = User("Ashutosh", 28, "Ojha")
    fun getUserObj() = user

}