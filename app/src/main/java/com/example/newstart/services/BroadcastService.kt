package com.example.newstart.services

import android.app.Service
import android.content.Intent
import android.os.*
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import android.util.Log
import android.widget.Toast
import com.example.newstart.asds
import kotlinx.coroutines.delay
import java.lang.Process
import java.lang.Thread.sleep

/**
 * Created by Ashutosh Ojha on 22,September,2021
 */
class BroadcastService : Service() {
    private var serviceLooper: Looper? = null

    var count: Int = 0


    override fun onCreate() {

//        a = getMe()!!
    }

    private fun getMe(): asds? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job


        // If we get killed, after returning from here, restart
        val th = Thread(Runnable {
            kotlin.run {

                for (i in 1..10){
                count++;
                Log.d("ThradSleep", "$count")
                    sendDataToActivity()

                sleep(1000)
            }

                //Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()
            }
        })

        th.start()

        return START_STICKY
    }

    private fun sendDataToActivity() {
        val sendLevel = Intent()
         sendLevel.action = "GET_SIGNAL_STRENGTH"
        sendLevel.putExtra("LEVEL_DATA", count)
        sendBroadcast(sendLevel)
    }

    override fun onBind(intent: Intent): IBinder? {
        // We don't provide binding, so return null
        return null
    }

    override fun onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }
}
