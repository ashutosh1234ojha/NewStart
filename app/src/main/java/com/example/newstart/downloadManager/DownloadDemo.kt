package com.example.newstart.downloadManager


import android.app.DownloadManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.webkit.URLUtil
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import java.io.File


/**
 * https://github.com/commonsguy/cw-android/tree/master/Internet/Download
 * Created by Ashutosh Ojha on 29,March,2022
 */


class DownloadDemo : AppCompatActivity() {
    val imageURL =
        "http://ichef.bbci.co.uk/onesport/cps/480/cpsprodpb/11136/production/_95324996_defoe_rex.jpg"
    lateinit var tvCM: TextView
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1 = findViewById<TextView>(R.id.btn1)
        tvCM = findViewById<TextView>(R.id.tvCM)
        btn1.setOnClickListener {
            download(imageURL)
        }
    }

    private fun download(url: String) {
        val filename = URLUtil.guessFileName(url, null, null)
        val file = File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString(), filename)
        var request: DownloadManager.Request? = null

        request = when {
            isDownloadManagerEqualOrAboveNougat() -> DownloadManager.Request(Uri.parse(url)).apply {
                setTitle(filename)
                setDescription(getString(R.string.str_desc_downloading))
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                setDestinationUri(Uri.fromFile(file))
                setRequiresCharging(false)
                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
            }
            else -> DownloadManager.Request(Uri.parse(url)).apply {
                setTitle(filename)
                setDescription(getString(R.string.str_desc_downloading))
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                setDestinationUri(Uri.fromFile(file))
                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
            }
        }
        val downloadManager =
            getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    private fun isDownloadManagerEqualOrAboveNougat(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }
}



