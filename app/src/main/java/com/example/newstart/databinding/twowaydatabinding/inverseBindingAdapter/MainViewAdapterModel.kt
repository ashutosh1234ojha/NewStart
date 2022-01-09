package com.example.newstart.databinding.twowaydatabinding.inverseBindingAdapter

import androidx.lifecycle.ViewModel
import com.example.newstart.databinding.twowaydatabinding.Cities
import com.example.newstart.databinding.twowaydatabinding.Gender
import com.example.newstart.databinding.twowaydatabinding.User

/**
 * Created by Ashutosh Ojha on 09,January,2022
 */
class MainViewAdapterModel : ViewModel() {

    val user = User("ojha@email.com", "ashu1234", Gender.MALE, Cities.DELHI)
}