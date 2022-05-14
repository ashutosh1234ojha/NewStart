package com.example.newstart.multiscreen.slidingpane

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R


/**
 * Created by Ashutosh Ojha on 13,May,2022
 */
class SlidingPaneActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sliding_pane)
        supportFragmentManager.beginTransaction().add(R.id.container, ListFragment()).commit()
//        supportFragmentManager.beginTransaction().add(R.id.detailsContainer, DetailsFragment())
//            .commit()

    }


}