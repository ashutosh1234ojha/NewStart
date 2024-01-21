package com.example.newstart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Ashutosh Ojha on 03,January,2022
 */
class KotlinPlayGround : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.constraint_layout_play)

        runCatching {
            3.8/4
        }.getOrNull()
    }

}