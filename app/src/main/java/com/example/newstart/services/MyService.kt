package com.example.newstart.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.newstart.R
import com.example.newstart.room.MyInfoDatabase

class MyService : Service() {
    var thread = Thread()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        foreGroundService()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val channelId = "my_service"
        val channelName = "My Background Service"
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_HIGH
        )
        chan.lightColor = Color.BLUE
        chan.importance = NotificationManager.IMPORTANCE_NONE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun foreGroundService() {
        val pendingIntent: PendingIntent =
            Intent(this, MyService::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }

        val notification: Notification = Notification.Builder(this, createNotificationChannel())
            .setContentTitle("R.string.notification_title")
            .setContentText("notification_message")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setTicker("getText(R.string.ticker_text)")
            .build()

// Notification ID cannot be 02
        // ContextCompat.startForegroundService(pendingIntent,notification)
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

val data=MyInfoDatabase.getInstance(applicationContext).noteDao();
        val t: Thread = object : Thread() {
            override fun run() {
                try {
                    for (i in 1..100) {
                        Log.d("MyService", "My number $i")
                        //    data.insetMyInfo(MyInfo(i, "" + i, "" + i,""))
//                        for (i in 1..100) {
//                            Log.d("MyService", "My number $i")
//                            for (i in 1..100) {
//                                Log.d("MyService", "My number $i")
//                                for (i in 1..100) {
//                                    Log.d("MyService", "My number $i")
//                                }
//                            }
//                        }
                    }
                } finally {
                }
            }
        }
        t.start()

        return super.onStartCommand(intent, flags, startId)
    }


}