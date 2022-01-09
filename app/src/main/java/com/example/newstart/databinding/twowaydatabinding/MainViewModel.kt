package com.example.newstart.databinding.twowaydatabinding

import androidx.lifecycle.ViewModel

/**
 * Created by Ashutosh Ojha on 09,January,2022
 */
class MainViewModel : ViewModel() {

    val user = User("ojha@email.com", "ashu1234", Gender.MALE, Cities.DELHI)
}