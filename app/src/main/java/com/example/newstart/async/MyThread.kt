package com.example.newstart.async

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log

/**
 * Created by Ashutosh Ojha on 15,August,2021
 */
class MyThread(private val s: Array<String>, private val handler: Handler,private val   handler1: Handler) :Thread() {

    override fun run() {
        for(i in s.indices){
            Log.d("ThreadTest","Thread Start ${s[i]}")
            Log.d("ThreadTest",Thread.currentThread().name)
            Thread.sleep(5000)

            val msg=Message()
            val bundle=Bundle()
            bundle.putString("str",s[i])
            msg.data=bundle
            if(i==0){
                handler1.sendMessage(msg)

            }else{
                handler.sendMessage(msg)

            }
            Log.d("ThreadTest","Thread End ${s[i]}")
        }


    }
}