package com.example.newstart.liveFlowdata

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 09,February,2022
 */
class LiveDataActivity : AppCompatActivity() {
    val liveViewModel: LiveViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        val tvCount = findViewById<TextView>(R.id.tvCount)
        val btn = findViewById<TextView>(R.id.btn)
        val btn1 = findViewById<TextView>(R.id.btn1)
        val btn2 = findViewById<TextView>(R.id.btn2)

        btn.setOnClickListener {
            liveViewModel.increaseCount()
        }
        btn1.setOnClickListener {
            liveViewModel.sourceOne()
        }
        btn2.setOnClickListener {
           // liveViewModel.sourceTwo()
            liveViewModel.transformation()
        }

        liveViewModel.countLiveData.observe(this, Observer {
            Log.d("CountDownTimer", "Activity ${it}")

            tvCount.text = "$it"
        })

        liveViewModel.mediatorLiveData.observe(this, Observer {
            Log.d("CountDownTimer", "Activity ${it}")

            tvCount.text = "$it"
        })

        liveViewModel._transformLiveData1.observe(this, {
            tvCount.text = "${it.get(0).toString()}"

        })
    }
}