package com.example.newstart.databinding.twowaydatabinding.inverseBindingAdapter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newstart.R
import com.example.newstart.databinding.ActivityTwoWayAdapterBinding

/**
 * Created by Ashutosh Ojha on 09,January,2022
 */

class TwoWayAdapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTwoWayAdapterBinding
    private lateinit var viewModel: MainViewAdapterModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_adapter)
        title = "User Registration Form"
        viewModel = ViewModelProvider(this).get(MainViewAdapterModel::class.java)

        binding.handler = this
        binding.viewModel = viewModel


    }

    fun runCode(v: View) {
        /*this is just a demo application for learning purposes
        that's why there is no validation on any input widgets/views
        use validation when developing production application
        */

//        val email = binding.etEmail.text.toString()
//        val username = binding.etUsername.text.toString()
//        val gender =
//            if (binding.rgGender.checkedRadioButtonId == R.id.rb_male) Gender.MALE else Gender.FEMALE

//        var selectedCity: Cities = Cities.CHANDIGARH
//
//        for (city in Cities.values()) {
//            if (binding.spinnerCity.selectedItemPosition == city.ordinal)
//                selectedCity = city
//        }

//        viewModel.user.email = email
//        viewModel.user.username = username
//        viewModel.user.gender = gender
//        viewModel.user.city = selectedCity

//        binding.viewModel = viewModel

        Toast.makeText(this, "Hello, ${viewModel.user.username}", Toast.LENGTH_SHORT).show()

    }
}