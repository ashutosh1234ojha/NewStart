package com.example.newstart.navigatonview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.newstart.R


class DashboardActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val navController =
            Navigation.findNavController(this, R.id.my_nav_host_fragment)



    }



}