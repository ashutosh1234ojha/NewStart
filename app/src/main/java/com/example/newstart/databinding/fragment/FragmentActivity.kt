package com.example.newstart.databinding.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 28,December,2021
 */
class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        supportFragmentManager.beginTransaction()
            .add(R.id.container_frag, MainFragment(), "main_fragment").commit()
    }
}