package com.example.newstart.lifecycle

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 16,August,2021
 */
class ActivityB : AppCompatActivity() {
    val TAG = "LifeCycle"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        Log.d(TAG, "onCreate  B")

        val btn=findViewById<Button>(R.id.btn)
//        btn.setOnClickListener {
//
//        }
        finish()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart B")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart B")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume B")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause B")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop B")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy B")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState B")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState B")

    }
}