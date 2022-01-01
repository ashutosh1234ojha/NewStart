package com.example.newstart.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R

class MyBoundActivity :AppCompatActivity() {
    private lateinit var mService: MyBoundSerice
    private var mBound: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_main
        )

        val  btn=findViewById<Button>(R.id.btn)
        val  btn1=findViewById<Button>(R.id.btn1)
        btn.setOnClickListener {

            val intent= Intent(this, MyBoundSerice::class.java)
            bindService(intent,connection,Context.BIND_AUTO_CREATE)

//            Intent(this, MyBoundSerice::class.java).also { intent ->
//                bindService(intent, connection, Context.BIND_AUTO_CREATE)
//            }

        }

        btn1.setOnClickListener {
            if(mBound)
            {
              val num=  mService.randomNumber
                Toast.makeText(this, "number: $num", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val connection=object:ServiceConnection{
        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }

        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            val binder = service as MyBoundSerice.LocalBinder
            mService = binder.getService()
            mBound = true           }

    }


    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }
}