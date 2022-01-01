package com.example.newstart.services

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.MyClass
import com.example.newstart.R
import com.example.newstart.linkedlist.SingleLinkedList
import com.example.newstart.room.MyInfoDatabase

class StartServiceActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_service)

        var mt=MyClass()
     //   mt.add()

//        foreGroundService()



        }

    }

    var s: Int = 1
    fun smallestpositive(array: Array<Int>, n: Int): String {

        check(array,s);
        for (i in array.indices) {
            var sum = array[i]
            for (j in array.indices) {
                if (i == j) {
                    continue
                } //
                sum = sum + array[j]
                if (s == sum) {
                    s++
                   check(array,s);

                }
            }
        }
        return "$s"
        // Your code goes here
    }

    fun check(array: Array<Int>, n: Int){
        for (i in array.indices) {
            if (s == array[i]) {
                s++
            }
        }
    }


    fun getFact( N:Int):String{

        if(N==0||N==1){
            return "1";
        }
        if(N==2){
            return "2";
        }
        var temp=N;
        var nn=N;
        while(nn>1){
            temp=temp*--nn;

        }
        return "$temp";
    }



