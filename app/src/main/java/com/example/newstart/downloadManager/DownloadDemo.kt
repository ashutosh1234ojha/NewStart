package com.example.newstart.downloadManager

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.newstart.R
import java.io.File
import java.net.URLConnection


/**
 * https://github.com/commonsguy/cw-android/tree/master/Internet/Download
 * Created by Ashutosh Ojha on 29,March,2022
 */


class DownloadDemo : Activity() {
    private val REQUEST_CODE = 100
    val imageURL = "http://ichef.bbci.co.uk/onesport/cps/480/cpsprodpb/11136/production/_95324996_defoe_rex.jpg"
//    val imageURL = "https://notes-apps.000webhostapp.com/android/demoImage.png"
    var imageName = "demoImage.jpg"
    private var mgr: DownloadManager? = null
    lateinit var tvCM: TextView
    private var lastDownload = -1L
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // storage runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_CODE
                );
            }
        }
        mgr = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        registerReceiver(
            onComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
        registerReceiver(
            onNotificationClick,
            IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        )

        tvCM = findViewById<TextView>(R.id.tvCM)
        tvCM.setOnClickListener {
          //  downloadTask("http://ichef.bbci.co.uk/onesport/cps/480/cpsprodpb/11136/production/_95324996_defoe_rex.jpg")
            //    startDownload(tvCM)
            downloadImage(imageURL, imageName);

        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(onComplete)
        unregisterReceiver(onNotificationClick)
    }

    fun startDownload(v: View) {
//             val uri = Uri.parse("https://file-examples.com/storage/fe62cb76d16242e7a5d758b/2017/04/file_example_MP4_1920_18MG.mp4")
//        val uri =
//            Uri.parse("https://file-examples.com/storage/fe62cb76d16242e7a5d758b/2017/10/file-example_PDF_1MB.pdf")
        val url = Uri.parse("http://commonsware.com/misc/test.mp4")
//        Environment
//            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//            .mkdirs()

        var path =
            "http://ichef.bbci.co.uk/onesport/cps/480/cpsprodpb/11136/production/_95324996_defoe_rex.jpg"
        var uri =
            Uri.parse("http://ichef.bbci.co.uk/onesport/cps/480/cpsprodpb/11136/production/_95324996_defoe_rex.jpg")


        lastDownload = mgr!!.enqueue(
            DownloadManager.Request(uri)
                .setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI or
                            DownloadManager.Request.NETWORK_MOBILE
                )
                .setAllowedOverRoaming(false)
                .setTitle("newFile")
                .setMimeType(URLConnection.guessContentTypeFromName(path))
                .setDescription("Something useful. No, really.")
                .setDestinationInExternalFilesDir(
                    this,
                    Environment.DIRECTORY_DOWNLOADS,
                    "fileName.jpeg"
                )             //  .setDestinationInExternalPublicDir( file, "downloadManager.pdf")
//                .setDestinationInExternalFilesDir(this, cacheDir.absolutePath, "downloadManager.pdf")
//                .setDestinationInExternalPublicDir(
//                    Environment.DIRECTORY_DOWNLOADS,
//                    "test.pdf"
//                )
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
            tvCM.isEnabled = true

            //    findViewById<View>(R.id.start).isEnabled = true
        }
    }
    var onNotificationClick: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            Toast.makeText(ctxt, "Ummmm...hi!", Toast.LENGTH_LONG).show()
        }
    }

    fun download() {
        val uri =
            Uri.parse("https://file-examples.com/storage/fe62cb76d16242e7a5d758b/2017/04/file_example_MP4_1920_18MG.mp4")

        val request = DownloadManager.Request(uri)

    }

    //    I found an solution on Stackoverflow (Can't find the link anymore)
    fun downloadTask(url: String): Boolean {
        if (!url.startsWith("http")) {
            return false;
        }
        var name = "temp.mcaddon";
        try {
//            var file = File(Environment.DIRECTORY_DOWNLOADS, "Download");
//            if (!file.exists()) {
//                //noinspection ResultOfMethodCallIgnored
//                file.mkdirs();
//            }
            val path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            )
            val file = File(path, "DemoPicture.jpg")

            //      var result = File(this.cacheDir.parent, "ImageLLL.jpeg");
            var downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var request = DownloadManager.Request(Uri.parse(url));
            request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI or
                        DownloadManager.Request.NETWORK_MOBILE
            )
            //      request.setDestinationUri(Uri.fromFile(file));
          //  request.setDestinationInExternalPublicDir()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            if (downloadManager != null) {
                downloadManager.enqueue(request);
            }
            //mToast(mContext, "Starting download...");
//            MediaScannerConnection.scanFile(this,  String[]{result.toString()}, null,
//                new MediaScannerConnection.OnScanCompletedListener() {
//                    public void onScanCompleted(String path, Uri uri) {
//                    }
//                });
        } catch (e: Exception) {
            Log.e(">>>>>", e.toString());
            //mToast(this, e.toString());
            return false;
        }
        return true;
    }

    fun downloadImage(url: String?, outputFileName: String?) {
        val request = DownloadManager.Request(Uri.parse(url))
        request.setTitle(imageName)
        request.setDescription("Downloading $imageName")
        request.setAllowedNetworkTypes(
            DownloadManager.Request.NETWORK_WIFI or
                    DownloadManager.Request.NETWORK_MOBILE
        )
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.allowScanningByMediaScanner()
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, outputFileName)
        val manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }


}