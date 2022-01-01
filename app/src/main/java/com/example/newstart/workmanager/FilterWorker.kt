package com.example.newstart.workmanager

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.work.*
import com.example.newstart.workmanager.room.CourseDatabase
import com.example.newstart.workmanager.room.CourseModal
import java.text.SimpleDateFormat
import java.util.*
import androidx.work.ForegroundInfo

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager

import androidx.core.app.NotificationCompat

import android.os.Build

import androidx.work.WorkManager

import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE

import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService


/**
 * Created by Ashutosh Ojha on 30,November,2021
 */
private const val LOG_TAG = "FilterWorker"
const val KEY_IMAGE_URI = "IMAGE_URI"
const val KEY_IMAGE_INDEX = "IMAGE_INDEX"

private const val IMAGE_PATH_PREFIX = "IMAGE_PATH_"

class FilterWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {


    override fun doWork(): Result {
        // Sleep for debugging purposes
        try {


            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

         //   setForeground(createForegroundInfo(progress))
            setForegroundAsync(createForegroundInfo("10"));

            while (true){
                Thread.sleep(2000)

                val currentDate = sdf.format(Date())
                // System.out.println(" C DATE is  "+currentDate)
                CourseDatabase.getInstance(context)?.Dao()?.insert(CourseModal(currentDate))
            }



            Log.d(LOG_TAG, "Applying filter to image!")
            Log.d(LOG_TAG, "current  thread ${Thread.currentThread().name}")

            val imageUriString = inputData.getString(KEY_IMAGE_URI)
            val imageIndex = inputData.getInt(KEY_IMAGE_INDEX, 0)

            val bitmap = MediaStore.Images.Media.getBitmap(
                applicationContext.contentResolver,
                Uri.parse(imageUriString)
            )

            val filteredBitmap = ImageUtils.applySepiaFilter(bitmap)
            val filteredImageUri = ImageUtils.writeBitmapToFile(applicationContext, filteredBitmap)

            val outputData =
                Data.Builder()
                    .putString(IMAGE_PATH_PREFIX + imageIndex, filteredImageUri.toString())
                    .build()

            Log.d(LOG_TAG, "Success!")
            return Result.success()
        } catch (e: Throwable) {
            Log.e(LOG_TAG, "Error executing work: " + e.message, e)
            return Result.failure()
        }

    }
    private fun createForegroundInfo(progress: String): ForegroundInfo {
        // Build a notification using bytesRead and contentLength
        val context = applicationContext
        val id = "9"
        val title = "notification_title"
        val cancel = "cancel_download"
        // This PendingIntent can be used to cancel the worker
        val intent = WorkManager.getInstance(context)
            .createCancelPendingIntent(getId())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
        val notification: Notification = NotificationCompat.Builder(context, id)
            .setContentTitle(title)
            .setTicker(title)
            .setSmallIcon(R.drawable.ic_delete)
            .setOngoing(true) // Add the cancel action to the notification which can
            // be used to cancel the worker
            .addAction(R.drawable.ic_delete, cancel, intent)
            .build()
        return ForegroundInfo(1,notification)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        // Create a Notification channel
        // Create the NotificationChannel
        val name = "channel_name"
        val descriptionText = "channel_description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel("9", name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
//        val notificationManager = getSystemService(context,Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(mChannel)
    }
}