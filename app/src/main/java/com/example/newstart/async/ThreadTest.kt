package com.example.newstart.async

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 15,August,2021
 */
class ThreadTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_thread)
        var value=""

        val btnStart = findViewById<Button>(R.id.btnStart)
        val tvTest = findViewById<TextView>(R.id.tvTest)

        val arrayname = arrayOf("Tere  naam", "Mein hoon  na","Kal ho  na  ho")


        val handler=object :Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                value += msg.data.getString("str")
                tvTest.text=value
            }
        }


        val handler1=object :Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                value += msg.data.getString("str")
                tvTest.text=value
            }
        }

//        val handler=Handler(Looper.getMainLooper()){
//
//        }
        btnStart.setOnClickListener {
//            for (i in 0..2) {
                val myThread=MyThread(arrayname,handler,handler1)
                myThread.start()
//            }
        }


    }
}