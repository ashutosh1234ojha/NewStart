package com.example.newstart.kotlinplayground

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 09,April,2022
 */
class MainActivity : AppCompatActivity(), DelegationInterface by DelegationImp() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)

        btn1.setOnClickListener {
            getOne()
            setOne()

            generics()
        }


    }

    fun  crewOfSloths(list: List<Mammal>) {

//        list =Sloth("")
        list.forEach {
            Log.d("crewOfSloths", "Eat")
        }

    }

    private fun generics() {

        val listOfSloth = listOf(
            Sloth(""),
            Sloth("")
        )
        crewOfSloths(listOfSloth)

        val listOfPanda = listOf(
            Panda(""),
            Panda("")
        )

        crewOfSloths(listOfPanda)
    }
}