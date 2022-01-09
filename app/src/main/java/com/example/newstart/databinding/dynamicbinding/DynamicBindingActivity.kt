package com.example.newstart.databinding.dynamicbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 07,January,2022
 */
class DynamicBindingActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_activity)

        val rv_logs=findViewById<RecyclerView>(R.id.rv_logs)
        rv_logs.layoutManager = LinearLayoutManager(this)
        rv_logs.adapter = PersonAdapter(User.userList)
    }
}