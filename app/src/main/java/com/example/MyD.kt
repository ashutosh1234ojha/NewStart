package com.example

import android.util.Log
import java.lang.Thread.currentThread
import java.net.Authenticator

/**
 * Created by Ashutosh Ojha on 03,September,2021
 */
class MyD() : Authenticator() {

    fun createMultipleThread() {

        Log.d("createMultipleThread", "Thread Main" + currentThread().hashCode())

        for(i in 1..100){

            Thread {
                Log.d("createMultipleThread", "Thread $i" + currentThread().hashCode())
                Thread.sleep(1000)
            }.start()
        }

//        Thread {
//            Log.d("createMultipleThread", "Thread 1" + currentThread().name)
//        }.start()
//
//
//
//        object : Thread() {
//            override fun run() {
//                super.run()
//                Log.d("createMultipleThread", "Thread 2" + currentThread().name)
//
//            }
//        }.start()
//
//        Thread {
//            Log.d("createMultipleThread", "Thread 1" + currentThread().name)
//        }.start()
//
//
//        Thread {
//            Log.d("createMultipleThread", "Thread 1" + currentThread().name)
//        }.start()


//        Thread {
//            Log.d("createMultipleThread", "Thread 1" + currentThread().name)
//        }.start()

    }
}

interface av {
    companion object {

        val a = "dd"
    }
}