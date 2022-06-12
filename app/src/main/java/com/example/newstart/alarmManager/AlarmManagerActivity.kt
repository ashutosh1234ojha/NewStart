package com.example.newstart.alarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R


/**
 * Created by Ashutosh Ojha on 03,June,2022
 */
class AlarmManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)

        val input = findViewById<EditText>(R.id.input)
        val btn1 = findViewById<Button>(R.id.btn)
        btn1.setOnClickListener {

            startAlert(input.text.toString())
        }
    }

    private fun startAlert(toString: String) {
      //  val text = findViewById<EditText>(R.id.time)
        val i = toString.toInt()
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this.applicationContext, 234324243, intent, 0
        )
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager


        alarmManager[AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + i * 1000] = pendingIntent
        Toast.makeText(this, "Alarm set in $i seconds", Toast.LENGTH_LONG).show()
    }
}