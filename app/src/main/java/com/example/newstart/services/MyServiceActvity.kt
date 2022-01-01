package com.example.newstart.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.example.newstart.MyClass
import com.example.newstart.R
import com.example.newstart.navigatonview.MyHomeFragmentDirections

/**
 * Created by Ashutosh Ojha on 22,September,2021
 */
class MyServiceActvity : AppCompatActivity() {
    lateinit var btn1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener {
            //  startService(Intent(this, BroadcastService::class.java))

            showLiveData()
            val abc = MyClass()
            abc.add()

            val intent = Intent(this, MyIntentService::class.java)
            for (i in 0..5) {
                intent.action = "action $i"
                startService(intent)
            }
            Log.d("MyServiceActvity", Thread.currentThread().name)
            val receiver = MyBroadcastReceiver()

            registerReceiver(receiver, IntentFilter())  //<----Register

        }
        val receiver = WifiLevelReceiver()

        registerReceiver(receiver, IntentFilter("GET_SIGNAL_STRENGTH"))  //<----Register

    }

    private fun showLiveData() {
        val dataList: MutableLiveData<Int> = MutableLiveData()
        dataList.value=5

        val data: LiveData<Int> = Transformations.map(dataList) {
            it * it
        }
        Log.d("showLiveData",data.value.toString())

        data.observe(this, Observer {
            Log.d("observe",data.value.toString())

        })

//        dataList.
    }


    inner class WifiLevelReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            if (intent!!.action.equals("GET_SIGNAL_STRENGTH")) {
                val level: Int = intent.getIntExtra("LEVEL_DATA", 0)
                btn1.text = "$level"

            }
        }

    }

    class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val result = intent.getStringExtra("EXTRA_KEY_UPDATE")
            // textResult.setText(result)
            Log.d("EXTRA_KEY_UPDATE", result!!)
        }
    }


}
