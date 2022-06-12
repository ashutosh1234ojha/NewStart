package com.example.newstart.alarmManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.provider.Settings
import android.widget.Toast


/**
 * Created by Ashutosh Ojha on 03,June,2022
 */
class MyBroadcastReceiver : BroadcastReceiver() {
    var mp: MediaPlayer? = null
    override fun onReceive(context: Context?, intent: Intent?) {
       mp = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI)
        mp!!.start()
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show()
    }
}