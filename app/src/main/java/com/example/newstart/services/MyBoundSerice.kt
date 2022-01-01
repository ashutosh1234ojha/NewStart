package com.example.newstart.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class MyBoundSerice :Service() {
    private val binder=LocalBinder()
    // Random number generator
    private val mGenerator = Random()
    companion object{
        val TAG="MyBoundSerice"
    }
    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG,"onBind")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"onStartCommand")
        return super.onStartCommand(intent, flags, startId)

    }

    inner class LocalBinder : Binder() {
        fun getService():MyBoundSerice{
            return this@MyBoundSerice;
        }

    }
    /** method for clients  */
    val randomNumber: Int
        get() = mGenerator.nextInt(100)
}