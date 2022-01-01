package com.example.newstart.lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.newstart.R


/**
 * Created by Ashutosh Ojha on 16,August,2021
 */
class ActivityA : AppCompatActivity() {
    val TAG = "LifeCycle"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate  A")

        val btn = findViewById<Button>(R.id.btn)
        val btn1 = findViewById<Button>(R.id.btn1)


        supportFragmentManager.beginTransaction()
            .add(R.id.container, FragmentA(), "FragmentA").addToBackStack("").commit()

        btn.setOnClickListener {

           // startActivity(Intent(this, ActivityB::class.java))

            supportFragmentManager.beginTransaction()
                .add(R.id.container, FragmentA(), "FragmentA").addToBackStack("").commit()
        }
        btn1.setOnClickListener {

//            startActivity(Intent(this, ActivityB::class.java))

            supportFragmentManager.beginTransaction()
                .add(R.id.container, FragmentB(), "FragmentB").addToBackStack("").commit()
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart A")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart A")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume A")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause A")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop A")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy A")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState A")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState A")

    }
}