package com.example.newstart.services

import android.R.id
import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.util.logging.Handler
import android.R.id.message

import android.os.Looper

import android.R.string.no
import android.R.string.no







/**
 * Created by Ashutosh Ojha on 20,October,2021
 */
class MyIntentService : IntentService("") {

    override fun onHandleIntent(intent: Intent?) {

        Log.d("MyIntentService", intent!!.action.toString())

        Log.d("MyIntentService", Thread.currentThread().name)

//        val r: (Int)->Int=( a:Int)->{}
        val runa=object:Runnable{
            override fun run() {
//                Toast.makeText(applicationContext,"Message",Toast.LENGTH_LONG).show()
                val intentUpdate = Intent()
//        intentUpdate.action = ACTION_MyUpdate
                intentUpdate.putExtra("EXTRA_KEY_UPDATE", intent.action.toString())
                sendBroadcast(intentUpdate)
            }
        }
        android.os.Handler().postDelayed(runa,6000)
//        android.os.Handler(Looper.getMainLooper()).postDelayed(runa,6000)




//        if(intent.action.toString() == "action 3"){
//            stopSelf()
//        }
        Thread.sleep(6000)


    }


}