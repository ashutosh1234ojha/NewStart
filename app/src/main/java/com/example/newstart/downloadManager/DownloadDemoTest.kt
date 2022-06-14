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


/**
 * https://github.com/commonsguy/cw-android/tree/master/Internet/Download
 * Created by Ashutosh Ojha on 29,March,2022
 */


class DownloadDemoTest : AppCompatActivity() {
    val imageURL =
//        "http://ichef.bbci.co.uk/onesport/cps/480/cpsprodpb/11136/production/_95324996_defoe_rex.jpg"
//        "https://ichef.bbci.co.uk/onesport/cps/480/cpsprodpb/11136/production/_95324996_defoe_rex.jpg"
        "https://africau.edu/images/default/sample.pdf"
    lateinit var tvCM: TextView
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1 = findViewById<TextView>(R.id.btn1)
        tvCM = findViewById<TextView>(R.id.tvCM)
        btn1.setOnClickListener {
            download()
        }
    }

    private fun download() {
       val url= "https://africau.edu/images/default/sample.pdf"
        val filename = URLUtil.guessFileName(url, null, null)
//        val file = File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString(), filename)
//        val imageCollection =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
//                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//            else MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//
//        val contentValue = ContentValues().apply {
//            put(MediaStore.Images.Media.DISPLAY_NAME, "displayName.jpg")
//            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
////            put(MediaStore.Images.Media.WIDTH, bmp.width)
////            put(MediaStore.Images.Media.HEIGHT, bmp.height)
//        }
//        val file = contentResolver.insert(imageCollection, contentValue).toString()
//
//        val f = File(imageCollection.toString(), filename)
        var request: DownloadManager.Request? = null

        request =
            DownloadManager.Request(Uri.parse(url)).apply {
                setTitle(filename)
                setDescription(getString(R.string.str_desc_downloading))
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                    //     setDestinationUri(Uri.fromFile(f))
                    .setDestinationInExternalFilesDir(
                        this@DownloadDemoTest,
                        Environment.DIRECTORY_DOWNLOADS,
                        "$filename.pdf"
                    )
              //  setRequiresCharging(false)
                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
            }
//            else -> DownloadManager.Request(Uri.parse(url)).apply {
//                setTitle(filename)
//                setDescription(getString(R.string.str_desc_downloading))
//                setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
//                    //  setDestinationUri(Uri.fromFile(f))
//                    .setDestinationInExternalFilesDir(
//                        this@DownloadDemo,
//                        Environment.DIRECTORY_DOWNLOADS,
//                        imageCollection.toString()
//                    )
//
//                setAllowedOverMetered(true)
//                setAllowedOverRoaming(true)
//            }
//        }
        val downloadManager =
            getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    private fun isDownloadManagerEqualOrAboveNougat(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }
}



