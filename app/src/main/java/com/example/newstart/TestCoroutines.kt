package com.example.newstart

import android.util.Log
import androidx.annotation.MainThread
import kotlinx.coroutines.*

/**
 * Created by Ashutosh Ojha on 15,August,2021
 */
class TestCoroutines {

    fun test(){
        Log.d("TestCoroutines",Thread.currentThread().getName())
        runBlocking(Dispatchers.IO) {
            // now we are inside a coroutine
            delay(2000L) // suspends the current coroutine for 2 seconds
            Log.d("TestCoroutines","Inside runblocking")
            Log.d("TestCoroutines",Thread.currentThread().getName())

        }

        CoroutineScope(Dispatchers.IO).launch {  }
        CoroutineScope(Dispatchers.IO).async {  }

        Log.d("TestCoroutines",Thread.currentThread().getName())

    }
}