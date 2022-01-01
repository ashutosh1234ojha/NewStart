package com.example.newstart

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class CoroutinesFile:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var i:Int
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_service)

        val btn=findViewById<Button>(R.id.btn)
        val get=findViewById<Button>(R.id.get)
        btn.setOnClickListener {


        }

        val s:Stack<String> =Stack<String>()


    }
}