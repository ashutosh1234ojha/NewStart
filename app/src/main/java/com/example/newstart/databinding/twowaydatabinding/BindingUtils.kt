//@file:JvmName("BindingUtils")

package com.example.newstart.databinding.twowaydatabinding

import androidx.databinding.InverseMethod
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 09,January,2022
 */


@InverseMethod("buttonIdToGender")
fun genderToButtonId(gender: Gender): Int {
    return if (gender == Gender.FEMALE) R.id.rb_female else R.id.rb_male
}

//Since we have defined this method as inverse in above annotation hence dataBinding will automatically find this out
fun buttonIdToGender(id: Int): Gender {
    return if (R.id.rb_male == id) Gender.MALE else Gender.FEMALE
}

@InverseMethod("positionToCity")
fun cityToPosition(city: Cities): Int {
    return city.ordinal
}

fun positionToCity(position: Int): Cities {
    return Cities.values()[position]
}