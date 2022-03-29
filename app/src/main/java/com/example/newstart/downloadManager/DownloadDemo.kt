package com.example.newstart.downloadManager
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.newstart.R


/**
 * https://github.com/commonsguy/cw-android/tree/master/Internet/Download
 * Created by Ashutosh Ojha on 29,March,2022
 */


class DownloadDemo : Activity() {
    private var mgr: DownloadManager? = null
    lateinit var tvCM:TextView
    private var lastDownload = -1L
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mgr = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        registerReceiver(
            onComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
        registerReceiver(
            onNotificationClick,
            IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        )

         tvCM=findViewById<TextView>(R.id.tvCM)
        tvCM.setOnClickListener { startDownload(tvCM) }
    }

    public override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(onComplete)
        unregisterReceiver(onNotificationClick)
    }

    fun startDownload(v: View) {
        val uri = Uri.parse("https://file-examples.com/storage/fe62cb76d16242e7a5d758b/2017/04/file_example_MP4_1920_18MG.mp4")
//        val uri = Uri.parse("https://file-examples.com/storage/fe62cb76d16242e7a5d758b/2017/10/file-example_PDF_1MB.pdf")
//        val uri = Uri.parse("http://commonsware.com/misc/test.mp4")
        Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .mkdirs()
        lastDownload = mgr!!.enqueue(
            DownloadManager.Request(uri)
                .setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI or
                            DownloadManager.Request.NETWORK_MOBILE
                )
                .setAllowedOverRoaming(false)
                .setTitle("Demo")
                .setDescription("Something useful. No, really.")
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    "test.mp4"
                )
        )
        v.isEnabled = false
//       findViewById<View>(R.id.query).isEnabled = true
    }

    @SuppressLint("Range")
    fun queryStatus(v: View?) {
        val c = mgr!!.query(DownloadManager.Query().setFilterById(lastDownload))
        if (c == null) {
            Toast.makeText(this, "Download not found!", Toast.LENGTH_LONG).show()
        } else {
            c.moveToFirst()
            Log.d(
                javaClass.name, "COLUMN_ID: " +
                        c.getLong(c.getColumnIndex(DownloadManager.COLUMN_ID))
            )
            Log.d(
                javaClass.name, "COLUMN_BYTES_DOWNLOADED_SO_FAR: " +
                        c.getLong(c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
            )
            Log.d(
                javaClass.name, "COLUMN_LAST_MODIFIED_TIMESTAMP: " +
                        c.getLong(c.getColumnIndex(DownloadManager.COLUMN_LAST_MODIFIED_TIMESTAMP))
            )
            Log.d(
                javaClass.name, "COLUMN_LOCAL_URI: " +
                        c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
            )
            Log.d(
                javaClass.name, "COLUMN_STATUS: " +
                        c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))
            )
            Log.d(
                javaClass.name, "COLUMN_REASON: " +
                        c.getInt(c.getColumnIndex(DownloadManager.COLUMN_REASON))
            )
            Toast.makeText(this, statusMessage(c), Toast.LENGTH_LONG).show()
        }
    }

    fun viewLog(v: View?) {
        startActivity(Intent(DownloadManager.ACTION_VIEW_DOWNLOADS))
    }

    @SuppressLint("Range")
    private fun statusMessage(c: Cursor): String {
        var msg = "???"
        msg =
            when (c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                DownloadManager.STATUS_FAILED -> "Download failed!"
                DownloadManager.STATUS_PAUSED -> "Download paused!"
                DownloadManager.STATUS_PENDING -> "Download pending!"
                DownloadManager.STATUS_RUNNING -> "Download in progress!"
                DownloadManager.STATUS_SUCCESSFUL -> "Download complete!"
                else -> "Download is nowhere in sight"
            }
        return msg
    }

    var onComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            Toast.makeText(ctxt, "Download done", Toast.LENGTH_LONG).show()
            tvCM.isEnabled=true

            //    findViewById<View>(R.id.start).isEnabled = true
        }
    }
    var onNotificationClick: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            Toast.makeText(ctxt, "Ummmm...hi!", Toast.LENGTH_LONG).show()
        }
    }
}